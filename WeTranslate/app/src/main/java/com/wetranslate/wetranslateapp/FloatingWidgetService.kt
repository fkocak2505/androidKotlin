package com.wetranslate.wetranslateapp

import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.graphics.PixelFormat
import android.graphics.Point
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import android.view.*
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.wetranslate.wetranslateapp.common.AWSTranslate


/**
 * Created by sonu on 28/03/17.
 */
class FloatingWidgetService : Service(), View.OnClickListener {
    private var mWindowManager: WindowManager? = null
    private var mFloatingWidgetView: View? = null
    private var collapsedView: View? = null
    private var expandedView: View? = null
    private var remove_image_view: ImageView? = null
    private val szWindow = Point()
    private var removeFloatingWidgetView: View? = null
    private var x_init_cord = 0
    private var y_init_cord = 0
    private var x_init_margin = 0
    private var y_init_margin = 0

    private lateinit var edt2BeTranslated: EditText
    private lateinit var txtTranslated: TextView

    //Variable to check if the Floating widget view is on left side or in right side
    // initially we are displaying Floating widget view to Left side so set it to true
    private var isLeft = true
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        //init WindowManager
        mWindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManagerDefaultDisplay

        //Init LayoutInflater
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        addRemoveView(inflater)
        addFloatingWidgetView(inflater)
        implementClickListeners()
        implementTouchListenerToFloatingWidgetView()
    }

    /*  Add Remove View to Window Manager  */
    private fun addRemoveView(inflater: LayoutInflater): View? {
        //Inflate the removing view layout we created
        removeFloatingWidgetView = inflater.inflate(R.layout.remove_floating_widget_layout, null)

        //Add the view to the window.
        val paramRemove: WindowManager.LayoutParams =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE,
                    PixelFormat.TRANSLUCENT
                )
            } else {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE,
                    PixelFormat.TRANSLUCENT
                )
            }

        //Specify the view position
        paramRemove.gravity = Gravity.TOP or Gravity.END

        //Initially the Removing widget view is not visible, so set visibility to GONE
        removeFloatingWidgetView?.visibility = View.GONE
        remove_image_view =
            removeFloatingWidgetView?.findViewById<View>(R.id.remove_img) as ImageView

        //Add the view to the window
        mWindowManager!!.addView(removeFloatingWidgetView, paramRemove)
        return remove_image_view
    }

    /*  Add Floating Widget View to Window Manager  */
    private fun addFloatingWidgetView(inflater: LayoutInflater) {
        //Inflate the floating view layout we created
        mFloatingWidgetView = inflater.inflate(R.layout.floating_widget_layout_2, null)

        //Add the view to the window.
        val params: WindowManager.LayoutParams =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE,
                    PixelFormat.TRANSLUCENT
                )
            } else {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE,
                    PixelFormat.TRANSLUCENT
                )
            }


        //Specify the view position
        params.gravity = Gravity.TOP or Gravity.END

        //Initially view will be added to top-left corner, you change x-y coordinates according to your need
        params.x = 0
        params.y = 200

        //Add the view to the window
        mWindowManager!!.addView(mFloatingWidgetView, params)

        //find id of collapsed view layout
        collapsedView = mFloatingWidgetView?.findViewById(R.id.collapse_view)

        //find id of the expanded view layout
        expandedView = mFloatingWidgetView?.findViewById(R.id.expanded_container)
    }

    private val windowManagerDefaultDisplay: Unit
        private get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) mWindowManager!!.defaultDisplay.getSize(
                szWindow
            ) else {
                val w = mWindowManager!!.defaultDisplay.width
                val h = mWindowManager!!.defaultDisplay.height
                szWindow[w] = h
            }
        }

    /*  Implement Touch Listener to Floating Widget Root View  */
    private fun implementTouchListenerToFloatingWidgetView() {
        //Drag and move floating view using user's touch action.
        mFloatingWidgetView!!.findViewById<View>(R.id.root_container)
            .setOnTouchListener(object : OnTouchListener {
                var time_start: Long = 0
                var time_end: Long = 0
                var isLongClick = false //variable to judge if user click long press
                var inBounded = false //variable to judge if floating view is bounded to remove view
                var remove_img_width = 0
                var remove_img_height = 0
                var handler_longClick = Handler()
                var runnable_longClick = Runnable { //On Floating Widget Long Click

                    //Set isLongClick as true
                    isLongClick = true

                    //Set remove widget view visibility to VISIBLE
                    removeFloatingWidgetView!!.visibility = View.VISIBLE
                    onFloatingWidgetLongClick()
                }

                override fun onTouch(v: View, event: MotionEvent): Boolean {

                    //Get Floating widget view params
                    val layoutParams =
                        mFloatingWidgetView!!.layoutParams as WindowManager.LayoutParams

                    //get the touch location coordinates
                    val x_cord = event.rawX.toInt()
                    val y_cord = event.rawY.toInt()
                    val x_cord_Destination: Int
                    var y_cord_Destination: Int
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            time_start = System.currentTimeMillis()
                            handler_longClick.postDelayed(runnable_longClick, 600)
                            remove_img_width = remove_image_view!!.layoutParams.width
                            remove_img_height = remove_image_view!!.layoutParams.height
                            x_init_cord = x_cord
                            y_init_cord = y_cord

                            //remember the initial position.
                            x_init_margin = layoutParams.x
                            y_init_margin = layoutParams.y
                            return true
                        }
                        MotionEvent.ACTION_UP -> {
                            isLongClick = false
                            removeFloatingWidgetView!!.visibility = View.GONE
                            remove_image_view!!.layoutParams.height = remove_img_height
                            remove_image_view!!.layoutParams.width = remove_img_width
                            handler_longClick.removeCallbacks(runnable_longClick)

                            //If user drag and drop the floating widget view into remove view then stop the service
                            if (inBounded) {
                                stopSelf()
                                inBounded = false
                            }


                            //Get the difference between initial coordinate and current coordinate
                            val x_diff = x_cord - x_init_cord
                            val y_diff = y_cord - y_init_cord

                            //The check for x_diff <5 && y_diff< 5 because sometime elements moves a little while clicking.
                            //So that is click event.
                            if (Math.abs(x_diff) < 5 && Math.abs(y_diff) < 5) {
                                time_end = System.currentTimeMillis()

                                //Also check the difference between start time and end time should be less than 300ms
                                if (time_end - time_start < 300) onFloatingWidgetClick()
                            }
                            y_cord_Destination = y_init_margin + y_diff
                            val barHeight = statusBarHeight
                            if (y_cord_Destination < 0) {
                                y_cord_Destination = 0
                            } else if (y_cord_Destination + (mFloatingWidgetView!!.height + barHeight) > szWindow.y) {
                                y_cord_Destination =
                                    szWindow.y - (mFloatingWidgetView!!.height + barHeight)
                            }
                            layoutParams.y = y_cord_Destination
                            inBounded = false

                            //reset position if user drags the floating view
                            resetPosition(x_cord)
                            return true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val x_diff_move = x_cord - x_init_cord
                            val y_diff_move = y_cord - y_init_cord
                            x_cord_Destination = x_init_margin + x_diff_move
                            y_cord_Destination = y_init_margin + y_diff_move

                            //If user long click the floating view, update remove view
                            if (isLongClick) {
                                val x_bound_left = szWindow.x / 2 - (remove_img_width * 1.5).toInt()
                                val x_bound_right =
                                    szWindow.x / 2 + (remove_img_width * 1.5).toInt()
                                val y_bound_top = szWindow.y - (remove_img_height * 1.5).toInt()

                                //If Floating view comes under Remove View update Window Manager
                                if (x_cord >= x_bound_left && x_cord <= x_bound_right && y_cord >= y_bound_top) {
                                    inBounded = true
                                    val x_cord_remove =
                                        ((szWindow.x - remove_img_height * 1.5) / 2).toInt()
                                    val y_cord_remove =
                                        (szWindow.y - (remove_img_width * 1.5 + statusBarHeight)).toInt()
                                    if (remove_image_view!!.layoutParams.height == remove_img_height) {
                                        remove_image_view!!.layoutParams.height =
                                            (remove_img_height * 1.5).toInt()
                                        remove_image_view!!.layoutParams.width =
                                            (remove_img_width * 1.5).toInt()
                                        val param_remove =
                                            removeFloatingWidgetView!!.layoutParams as WindowManager.LayoutParams
                                        param_remove.x = x_cord_remove
                                        param_remove.y = y_cord_remove
                                        mWindowManager!!.updateViewLayout(
                                            removeFloatingWidgetView,
                                            param_remove
                                        )
                                    }
                                    layoutParams.x = x_cord_remove + Math.abs(
                                        removeFloatingWidgetView!!.width - mFloatingWidgetView!!.width
                                    ) / 2
                                    layoutParams.y = y_cord_remove + Math.abs(
                                        removeFloatingWidgetView!!.height - mFloatingWidgetView!!.height
                                    ) / 2

                                    //Update the layout with new X & Y coordinate
                                    mWindowManager!!.updateViewLayout(
                                        mFloatingWidgetView,
                                        layoutParams
                                    )
                                } else {
                                    //If Floating window gets out of the Remove view update Remove view again
                                    inBounded = false
                                    remove_image_view!!.layoutParams.height = remove_img_height
                                    remove_image_view!!.layoutParams.width = remove_img_width
                                    //onFloatingWidgetClick();
                                }
                            }
                            layoutParams.x = x_cord_Destination
                            layoutParams.y = y_cord_Destination

                            //Update the layout with new X & Y coordinate
                            mWindowManager!!.updateViewLayout(mFloatingWidgetView, layoutParams)
                            return true
                        }
                    }
                    return false
                }
            })
    }

    private fun implementClickListeners() {
        edt2BeTranslated = mFloatingWidgetView?.findViewById(R.id.textTranslate)!!
        txtTranslated = mFloatingWidgetView?.findViewById(R.id.translatedText)!!

        mFloatingWidgetView?.findViewById<Button>(R.id.translate)!!.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.translate -> {
                AWSTranslate.translate(applicationContext, edt2BeTranslated.text.toString(), txtTranslated)
            }
            /*R.id.close_expanded_view -> {
                collapsedView!!.visibility = View.VISIBLE
                expandedView!!.visibility = View.GONE
            }
            R.id.open_activity_button -> {
                val intent = Intent(this@FloatingWidgetService, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                stopSelf()
            }*/
        }
    }

    /*  on Floating Widget Long Click, increase the size of remove view as it look like taking focus */
    private fun onFloatingWidgetLongClick() {
        //Get remove Floating view params
        val removeParams = removeFloatingWidgetView!!.layoutParams as WindowManager.LayoutParams

        //get x and y coordinates of remove view
        val x_cord = (szWindow.x - removeFloatingWidgetView!!.width) / 2
        val y_cord = szWindow.y - (removeFloatingWidgetView!!.height + statusBarHeight)
        removeParams.x = x_cord
        removeParams.y = y_cord

        //Update Remove view params
        mWindowManager!!.updateViewLayout(removeFloatingWidgetView, removeParams)
    }

    /*  Reset position of Floating Widget view on dragging  */
    private fun resetPosition(x_cord_now: Int) {
        if (x_cord_now <= szWindow.x / 2) {
            isLeft = true
            moveToLeft(x_cord_now)
        } else {
            isLeft = false
            moveToRight(x_cord_now)
        }
    }

    /*  Method to move the Floating widget view to Left  */
    private fun moveToLeft(current_x_cord: Int) {
        val x = szWindow.x - current_x_cord
        object : CountDownTimer(500, 5) {
            //get params of Floating Widget view
            var mParams = mFloatingWidgetView!!.layoutParams as WindowManager.LayoutParams
            override fun onTick(t: Long) {
                val step = (500 - t) / 5
                mParams.x = 0 - (current_x_cord * current_x_cord * step).toInt()

                //If you want bounce effect uncomment below line and comment above line
                // mParams.x = 0 - (int) (double) bounceValue(step, x);


                //Update window manager for Floating Widget
                mWindowManager!!.updateViewLayout(mFloatingWidgetView, mParams)
            }

            override fun onFinish() {
                mParams.x = 0

                //Update window manager for Floating Widget
                mWindowManager!!.updateViewLayout(mFloatingWidgetView, mParams)
            }
        }.start()
    }

    /*  Method to move the Floating widget view to Right  */
    private fun moveToRight(current_x_cord: Int) {
        object : CountDownTimer(500, 5) {
            //get params of Floating Widget view
            var mParams = mFloatingWidgetView!!.layoutParams as WindowManager.LayoutParams
            override fun onTick(t: Long) {
                val step = (500 - t) / 5
                mParams.x =
                    (szWindow.x + current_x_cord * current_x_cord * step - mFloatingWidgetView!!.width).toInt()

                //If you want bounce effect uncomment below line and comment above line
                //  mParams.x = szWindow.x + (int) (double) bounceValue(step, x_cord_now) - mFloatingWidgetView.getWidth();

                //Update window manager for Floating Widget
                mWindowManager!!.updateViewLayout(mFloatingWidgetView, mParams)
            }

            override fun onFinish() {
                mParams.x = szWindow.x - mFloatingWidgetView!!.width

                //Update window manager for Floating Widget
                mWindowManager!!.updateViewLayout(mFloatingWidgetView, mParams)
            }
        }.start()
    }

    /*  Get Bounce value if you want to make bounce effect to your Floating Widget */
    private fun bounceValue(step: Long, scale: Long): Double {
        return scale * Math.exp(-0.055 * step) * Math.cos(0.08 * step)
    }

    /*  Detect if the floating view is collapsed or expanded */
    private val isViewCollapsed: Boolean
        private get() = mFloatingWidgetView == null || mFloatingWidgetView!!.findViewById<View>(R.id.collapse_view).visibility == View.VISIBLE

    /*  return status bar height on basis of device display metrics  */
    private val statusBarHeight: Int
        private get() = Math.ceil(25 * applicationContext.resources.displayMetrics.density.toDouble())
            .toInt()

    /*  Update Floating Widget view coordinates on Configuration change  */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        windowManagerDefaultDisplay
        val layoutParams = mFloatingWidgetView!!.layoutParams as WindowManager.LayoutParams
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (layoutParams.y + (mFloatingWidgetView!!.height + statusBarHeight) > szWindow.y) {
                layoutParams.y = szWindow.y - (mFloatingWidgetView!!.height + statusBarHeight)
                mWindowManager!!.updateViewLayout(mFloatingWidgetView, layoutParams)
            }
            if (layoutParams.x != 0 && layoutParams.x < szWindow.x) {
                resetPosition(szWindow.x)
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (layoutParams.x > szWindow.x) {
                resetPosition(szWindow.x)
            }
        }
    }

    /*  on Floating widget click show expanded view  */
    private fun onFloatingWidgetClick() {
        if (isViewCollapsed) {
            //When user clicks on the image view of the collapsed layout,
            //visibility of the collapsed layout will be changed to "View.GONE"
            //and expanded view will become visible.
            collapsedView!!.visibility = View.GONE
            expandedView!!.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        /*  on destroy remove both view from window manager */if (mFloatingWidgetView != null) mWindowManager!!.removeView(
            mFloatingWidgetView
        )
        if (removeFloatingWidgetView != null) mWindowManager!!.removeView(removeFloatingWidgetView)
    }
}

const util = require('util')

const Worker = function Worker() {
    this.eventTrial = (params) => {
        var _this = this;
        console.log("EventTrial: " + params);
    }

    this.trialEvent = (params) => {
        var _this = this;
        console.log("TrialEvent: " + params);
        _this.emit('eventTrial', params);
    }
}

util.inherits(Worker, require('events').EventEmitter)
module.exports = Worker;
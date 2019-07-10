const Worker = require("./Worker");
const worker = new Worker();

worker.on('eventTrial', params => {
    worker.eventTrial(params);
})

worker.on('trialEvent', params => {
    worker.trialEvent(params);
})

// ==================================================================
const startProcess = (msg) => {
    worker.trialEvent(msg);
}

// Export all methods
module.exports = {
    startProcess
};
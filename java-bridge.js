const { spawn } = require('child_process');
const path = require('path');

class JavaBridge {
    constructor() {
        this.javaClassPath = path.join(__dirname, 'src');
    }

    async bookAppointment(appointmentData) {
        return new Promise((resolve, reject) => {
            const { doctorId, patientName, appointmentDate, timeSlot } = appointmentData;
            
            // Create a temporary Java class to handle the booking
            const javaProcess = spawn('java', [
                '-cp', this.javaClassPath,
                'com.wipro.hospital.service.JavaBridgeService',
                'book',
                doctorId,
                patientName,
                appointmentDate,
                timeSlot
            ]);

            let output = '';
            let errorOutput = '';

            javaProcess.stdout.on('data', (data) => {
                output += data.toString();
            });

            javaProcess.stderr.on('data', (data) => {
                errorOutput += data.toString();
            });

            javaProcess.on('close', (code) => {
                if (code === 0) {
                    try {
                        const result = JSON.parse(output.trim());
                        resolve(result);
                    } catch (e) {
                        resolve({ success: true, message: output.trim() });
                    }
                } else {
                    reject(new Error(errorOutput || output));
                }
            });

            javaProcess.on('error', (error) => {
                reject(error);
            });
        });
    }

    async checkAvailability(doctorId, appointmentDate, timeSlot) {
        return new Promise((resolve, reject) => {
            const javaProcess = spawn('java', [
                '-cp', this.javaClassPath,
                'com.wipro.hospital.service.JavaBridgeService',
                'check',
                doctorId,
                appointmentDate,
                timeSlot
            ]);

            let output = '';
            let errorOutput = '';

            javaProcess.stdout.on('data', (data) => {
                output += data.toString();
            });

            javaProcess.stderr.on('data', (data) => {
                errorOutput += data.toString();
            });

            javaProcess.on('close', (code) => {
                if (code === 0) {
                    try {
                        const result = JSON.parse(output.trim());
                        resolve(result);
                    } catch (e) {
                        resolve({ available: output.trim() === 'true' });
                    }
                } else {
                    reject(new Error(errorOutput || output));
                }
            });

            javaProcess.on('error', (error) => {
                reject(error);
            });
        });
    }

    async getAllAvailability(appointmentDate) {
        return new Promise((resolve, reject) => {
            const javaProcess = spawn('java', [
                '-cp', this.javaClassPath,
                'com.wipro.hospital.service.JavaBridgeService',
                'availability',
                appointmentDate
            ]);

            let output = '';
            let errorOutput = '';

            javaProcess.stdout.on('data', (data) => {
                output += data.toString();
            });

            javaProcess.stderr.on('data', (data) => {
                errorOutput += data.toString();
            });

            javaProcess.on('close', (code) => {
                if (code === 0) {
                    try {
                        const result = JSON.parse(output.trim());
                        resolve(result);
                    } catch (e) {
                        resolve({ availability: {} });
                    }
                } else {
                    reject(new Error(errorOutput || output));
                }
            });

            javaProcess.on('error', (error) => {
                reject(error);
            });
        });
    }
}

module.exports = JavaBridge;

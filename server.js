const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const path = require('path');
const JavaBridge = require('./java-bridge');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(bodyParser.json());
app.use(express.static('frontend'));

// Initialize Java Bridge
const javaBridge = new JavaBridge();

// API Routes
app.post('/api/appointments', async (req, res) => {
    try {
        const { doctorId, patientName, appointmentDate, timeSlot } = req.body;
        
        // Validate input
        if (!doctorId || !patientName || !appointmentDate || !timeSlot) {
            return res.status(400).json({
                success: false,
                message: 'All fields are required'
            });
        }
        
        // Call Java backend to book appointment
        const result = await javaBridge.bookAppointment({
            doctorId,
            patientName,
            appointmentDate,
            timeSlot
        });
        
        if (result.success) {
            res.json({
                success: true,
                message: result.message,
                appointmentId: result.appointmentId,
                appointment: {
                    appointmentId: result.appointmentId,
                    doctorId,
                    patientName,
                    appointmentDate,
                    timeSlot
                }
            });
        } else {
            res.status(409).json({
                success: false,
                message: result.message
            });
        }
        
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Internal server error: ' + error.message
        });
    }
});

app.get('/api/availability', async (req, res) => {
    try {
        const { date } = req.query;
        const targetDate = date || new Date().toISOString().split('T')[0];
        
        // Call Java backend to get availability
        const result = await javaBridge.getAllAvailability(targetDate);
        
        res.json({
            success: true,
            availability: result.availability,
            bookedSlots: result.bookedSlots || []
        });
        
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching availability: ' + error.message
        });
    }
});

app.get('/api/appointments/:doctorId/:date/:timeSlot', async (req, res) => {
    try {
        const { doctorId, date, timeSlot } = req.params;
        
        // Call Java backend to check availability
        const result = await javaBridge.checkAvailability(doctorId, date, timeSlot);
        
        res.json({
            success: true,
            doctorId: doctorId,
            appointmentDate: date,
            timeSlot: timeSlot,
            available: result.available
        });
        
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error checking availability: ' + error.message
        });
    }
});

// Serve frontend
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'frontend', 'index.html'));
});

// Health check endpoint
app.get('/health', (req, res) => {
    res.json({
        status: 'OK',
        message: 'Hospital Appointment Booking System is running',
        timestamp: new Date().toISOString()
    });
});

// Start server
app.listen(PORT, () => {
    console.log(`🏥 Hospital Appointment Booking System`);
    console.log(`🚀 Server running on http://localhost:${PORT}`);
    console.log(`📋 API endpoints:`);
    console.log(`   POST /api/appointments - Book appointment`);
    console.log(`   GET /api/availability - Check all availability`);
    console.log(`   GET /api/appointments/:doctorId/:timeSlot - Check specific slot`);
    console.log(`💻 Frontend available at http://localhost:${PORT}`);
    console.log(`📝 Now using REAL Java backend integration!`);
    console.log(`   - Web interface calls actual Java classes`);
    console.log(`   - Pre-booked: D101 on 2024-01-15 at 10:00 AM, D102 on 2024-01-15 at 2:00 PM`);
});

module.exports = app;

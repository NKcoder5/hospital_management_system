// Hospital Appointment Booking System - Frontend JavaScript

class AppointmentBooking {
    constructor() {
        this.apiUrl = 'http://localhost:3000/api';
        this.initializeEventListeners();
        this.setMinDate();
    }

    initializeEventListeners() {
        const form = document.getElementById('appointmentForm');
        const checkAvailabilityBtn = document.getElementById('checkAvailability');

        form.addEventListener('submit', (e) => this.handleFormSubmit(e));
        checkAvailabilityBtn.addEventListener('click', () => this.checkAvailability());
    }

    setMinDate() {
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('appointmentDate').setAttribute('min', today);
    }

    async handleFormSubmit(e) {
        e.preventDefault();
        
        const formData = this.getFormData();
        if (!this.validateForm(formData)) {
            return;
        }

        this.showLoading(true);
        this.hideResult();

        try {
            const response = await this.bookAppointment(formData);
            this.showResult(response.message, response.success);
            
            if (response.success) {
                this.resetForm();
            }
        } catch (error) {
            this.showResult('Error: ' + error.message, false);
        } finally {
            this.showLoading(false);
        }
    }

    getFormData() {
        return {
            doctorId: document.getElementById('doctorId').value,
            patientName: document.getElementById('patientName').value,
            appointmentDate: document.getElementById('appointmentDate').value,
            timeSlot: document.getElementById('timeSlot').value
        };
    }

    validateForm(data) {
        if (!data.doctorId || !data.patientName || !data.appointmentDate || !data.timeSlot) {
            this.showResult('Please fill in all required fields.', false);
            return false;
        }
        return true;
    }

    async bookAppointment(appointmentData) {
        // Simulate API call since we don't have a real backend running
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                // Simulate the Java backend logic with date included
                const key = `${appointmentData.doctorId}_${appointmentData.appointmentDate}_${appointmentData.timeSlot}`;
                const bookedSlots = this.getBookedSlots();
                
                if (bookedSlots.includes(key)) {
                    reject(new Error(`DoctorUnavailableException: Doctor ${appointmentData.doctorId} is not available on ${appointmentData.appointmentDate} at ${appointmentData.timeSlot}`));
                } else {
                    const appointmentId = 'AID' + Math.floor(Math.random() * 1000).toString().padStart(3, '0');
                    bookedSlots.push(key);
                    localStorage.setItem('bookedSlots', JSON.stringify(bookedSlots));
                    resolve({
                        success: true,
                        message: `Appointment Confirmed: ${appointmentId}`,
                        appointmentId: appointmentId
                    });
                }
            }, 1500);
        });
    }

    getBookedSlots() {
        const stored = localStorage.getItem('bookedSlots');
        return stored ? JSON.parse(stored) : ['D101_2024-01-15_10:00 AM', 'D102_2024-01-15_2:00 PM'];
    }

    async checkAvailability() {
        const availabilityDiv = document.getElementById('availabilityResult');
        const bookedSlots = this.getBookedSlots();
        const selectedDate = document.getElementById('appointmentDate').value || new Date().toISOString().split('T')[0];
        
        const doctors = ['D101', 'D102', 'D103'];
        const timeSlots = ['9:00 AM', '10:00 AM', '11:00 AM', '2:00 PM', '3:00 PM', '4:00 PM'];
        
        let html = `<h4>Doctor Availability for ${selectedDate}</h4>`;
        
        doctors.forEach(doctor => {
            html += `<div class="doctor-section"><h5>Dr. ${doctor}</h5>`;
            timeSlots.forEach(slot => {
                const key = `${doctor}_${selectedDate}_${slot}`;
                const isBooked = bookedSlots.includes(key);
                const statusClass = isBooked ? 'slot-booked' : 'slot-available';
                const statusText = isBooked ? 'Booked' : 'Available';
                
                html += `<div class="slot-item ${statusClass}">
                    <span>${slot}</span>
                    <span>${statusText}</span>
                </div>`;
            });
            html += '</div>';
        });
        
        availabilityDiv.innerHTML = html;
        availabilityDiv.classList.remove('hidden');
    }

    showLoading(show) {
        const loading = document.getElementById('loading');
        if (show) {
            loading.classList.remove('hidden');
        } else {
            loading.classList.add('hidden');
        }
    }

    showResult(message, isSuccess) {
        const result = document.getElementById('result');
        result.textContent = message;
        result.className = `result ${isSuccess ? 'success' : 'error'}`;
        result.classList.remove('hidden');
    }

    hideResult() {
        const result = document.getElementById('result');
        result.classList.add('hidden');
    }

    resetForm() {
        document.getElementById('appointmentForm').reset();
    }
}

// Initialize the application when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    new AppointmentBooking();
});

// Test the Java backend logic simulation
console.log('Hospital Appointment Booking System Loaded');
console.log('Pre-booked slots: D101 on 2024-01-15 at 10:00 AM, D102 on 2024-01-15 at 2:00 PM');

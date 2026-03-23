// Toggle password visibility
function togglePassword(inputId, btn) {
    const input = document.getElementById(inputId);
    const icon = btn.querySelector('i');
    if (input.type === 'password') {
        input.type = 'text';
        icon.classList.replace('fa-eye', 'fa-eye-slash');
    } else {
        input.type = 'password';
        icon.classList.replace('fa-eye-slash', 'fa-eye');
    }
}

// Password strength indicator
const passwordInput = document.getElementById('password');
if (passwordInput && document.getElementById('strengthFill')) {
    passwordInput.addEventListener('input', function () {
        const val = this.value;
        const fill = document.getElementById('strengthFill');
        const text = document.getElementById('strengthText');
        let strength = 0;
        if (val.length >= 6) strength++;
        if (val.length >= 10) strength++;
        if (/[A-Z]/.test(val)) strength++;
        if (/[0-9]/.test(val)) strength++;
        if (/[^A-Za-z0-9]/.test(val)) strength++;

        const levels = [
            { pct: '0%', color: 'transparent', label: '' },
            { pct: '20%', color: '#ef4444', label: 'Rất yếu' },
            { pct: '40%', color: '#f97316', label: 'Yếu' },
            { pct: '60%', color: '#eab308', label: 'Trung bình' },
            { pct: '80%', color: '#3b82f6', label: 'Mạnh' },
            { pct: '100%', color: '#10b981', label: 'Rất mạnh' },
        ];
        const level = levels[strength];
        fill.style.width = level.pct;
        fill.style.background = level.color;
        text.textContent = level.label;
        text.style.color = level.color;
    });
}

// Auto-hide alerts
document.querySelectorAll('.alert').forEach(alert => {
    setTimeout(() => {
        alert.style.transition = 'opacity 0.5s ease';
        alert.style.opacity = '0';
        setTimeout(() => alert.remove(), 500);
    }, 4000);
});

// Animate course cards on scroll
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry, index) => {
        if (entry.isIntersecting) {
            entry.target.style.animationDelay = (index * 0.05) + 's';
            entry.target.style.animationName = 'fadeInUp';
            observer.unobserve(entry.target);
        }
    });
}, observerOptions);

document.querySelectorAll('.course-card, .enrollment-card').forEach(card => {
    card.style.opacity = '0';
    card.style.animation = 'none';
    observer.observe(card);
});

// Re-run animations
setTimeout(() => {
    document.querySelectorAll('.course-card, .enrollment-card').forEach((card, index) => {
        setTimeout(() => {
            card.style.opacity = '';
            card.style.animation = '';
            card.style.animationName = 'fadeInUp';
            card.style.animationDuration = '0.4s';
            card.style.animationFillMode = 'both';
        }, index * 60);
    });
}, 50);

// Confirm delete
document.querySelectorAll('[data-confirm]').forEach(el => {
    el.addEventListener('click', function(e) {
        if (!confirm(this.dataset.confirm)) {
            e.preventDefault();
        }
    });
});

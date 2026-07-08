// Main JavaScript

// Add to Cart with confirmation
function addToCart(productId) {
    console.log('Product added to cart: ' + productId);
}

// Remove from Cart
function removeFromCart(productId) {
    if (confirm('Are you sure you want to remove this item?')) {
        console.log('Product removed from cart: ' + productId);
    }
}

// Update Cart Quantity
function updateQuantity(productId, quantity) {
    if (quantity < 1) {
        removeFromCart(productId);
    } else {
        console.log('Quantity updated for product: ' + productId);
    }
}

// Add to Wishlist
function addToWishlist(productId) {
    console.log('Product added to wishlist: ' + productId);
}

// Remove from Wishlist
function removeFromWishlist(productId) {
    console.log('Product removed from wishlist: ' + productId);
}

// Format currency
function formatCurrency(value) {
    return '₹' + parseFloat(value).toFixed(2);
}

// Show notification
function showNotification(message, type = 'success') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.setAttribute('role', 'alert');
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    const container = document.querySelector('body');
    container.insertBefore(alertDiv, container.firstChild);
    
    setTimeout(() => {
        alertDiv.remove();
    }, 3000);
}

// Filter Products
function filterProducts(category) {
    console.log('Filtering products by category: ' + category);
}

// Sort Products
function sortProducts(sortBy) {
    console.log('Sorting products by: ' + sortBy);
}

// Search Products
function searchProducts(query) {
    console.log('Searching products for: ' + query);
}

// Validate Email
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

// Validate Phone
function validatePhone(phone) {
    const re = /^[0-9]{10}$/;
    return re.test(phone);
}

// Form Validation
function validateForm(formId) {
    const form = document.getElementById(formId);
    if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
    }
    form.classList.add('was-validated');
}

// Toggle Menu
function toggleMenu() {
    const menu = document.querySelector('.navbar-collapse');
    menu.classList.toggle('show');
}

// Image Lazy Loading
document.addEventListener('DOMContentLoaded', function() {
    const images = document.querySelectorAll('img[data-lazy]');
    if ('IntersectionObserver' in window) {
        const imageObserver = new IntersectionObserver((entries, observer) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const img = entry.target;
                    img.src = img.dataset.lazy;
                    img.removeAttribute('data-lazy');
                    observer.unobserve(img);
                }
            });
        });
        images.forEach(img => imageObserver.observe(img));
    }
});

// Smooth Scroll
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth'
            });
        }
    });
});

// Initialize tooltips and popovers
var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
})

console.log('SatPall Crochet - Main JavaScript Loaded');

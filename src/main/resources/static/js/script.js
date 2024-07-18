function togglePassword() {
    const passwordInput = document.getElementById('password');
    const toggleIcon = document.querySelector('.toggle-password');

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        toggleIcon.textContent = '👁️'; // Change the icon to a closed eye
    } else {
        passwordInput.type = 'password';
        toggleIcon.textContent = '🙈'; // Change the icon back to an open eye
    }
}

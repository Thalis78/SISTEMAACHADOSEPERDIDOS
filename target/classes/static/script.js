document.addEventListener("DOMContentLoaded", function () {
  const passwordField = document.getElementById("password");
  const toggleButton = document.querySelector(".password-toggle");
  const eyeIcon = toggleButton.querySelector(".eye-icon");

  toggleButton.addEventListener("click", function () {
    const isPassword = passwordField.type === "password";
    passwordField.type = isPassword ? "text" : "password";

    eyeIcon.src = isPassword
      ? "static/icons/eye.svg"
      : "static/icons/eye-off.svg";
  });
});
function confirmarExclusao() {
  window.location.href = "/confirmacao";
}

document.addEventListener("DOMContentLoaded", function () {
  const toastMessage = document.getElementById("toast-message");

  if (toastMessage && toastMessage.classList.contains("show-toast")) {
    toastMessage.classList.remove("hidden");

    setTimeout(() => {
      toastMessage.style.transition = "opacity 0.5s ease, transform 0.5s ease";
      toastMessage.style.opacity = "0";
      toastMessage.style.transform = "translateY(-20px)";

      setTimeout(() => {
        toastMessage.classList.add("hidden");
        toastMessage.classList.remove("show-toast");
      }, 500);
    }, 4000);
  }
});

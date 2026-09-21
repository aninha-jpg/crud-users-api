const signupForm = document.getElementById("signupForm");

signupForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    try {
        const response = await fetch("http://localhost:8080/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: nome,
                email: email,
                senha: senha,
                usersProfile: "CLIENT"
            })
        });

        if (!response.ok) {
            throw new Error("Não foi possível realizar o cadastro.");
        }

        alert("Cadastro realizado com sucesso!");

        window.location.href = "../login/index.html";

    } catch (error) {
        alert(error.message);
    }
});
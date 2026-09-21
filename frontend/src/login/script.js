const form = document.getElementById("loginForm");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    try {
        // tenta conectar na api, se falhar ele retorna mensagem padrão.
        const response = await fetch ("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        });

        if(!response.ok){
            throw new Error("Email ou senha incorretos!")
        }

        const token = await response.text();

        localStorage.setItem("token", token);

        alert("Login realizado com sucesso!");
        window.location.href = "../home/home.html";


    } catch(error){
        alert(error.message);
    }
});

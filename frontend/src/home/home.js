const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "../login/login.html";
}

document.getElementById("buscarDados").addEventListener("click", async () => {

    try{

        // basicamente tenta conectar e puxar os dados se der certo só mostra na tela

        const response = await fetch("http://localhost:8080/users/me", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }

        });

        if(!response.ok){
            throw new Error("Não foi possível buscar os dados");
        }

        const user = await response.json();

        document.getElementById("dados").innerHTML = `
        <p>Nome: ${user.name}</p>
        <p>E-mail: ${user.email}</p>`;

    } catch (error){
        console.error(error);
        alert(error.message);
    }

});

// Sair
document.getElementById("logout").addEventListener("click", () => {

    localStorage.removeItem("token");

    window.location.href = "../login/login.html";
});
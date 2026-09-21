const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "../login/login.html";
}

const conteudo = document.getElementById("conteudo");

// Listar usuários
document.getElementById("buscarUsuarios").addEventListener("click", async () => {

    try {
        const response = await fetch("http://localhost:8080/admin", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Não foi possível buscar os usuários.");
        }

        const usuarios = await response.json();

        conteudo.innerHTML = "";

        usuarios.forEach(user => {

            conteudo.innerHTML += `
                <div>
                    <p>ID: ${user.id}</p>
                    <p>Nome: ${user.name}</p>
                    <p>E-mail: ${user.email}</p>

                    <button onclick="atualizarUsuario(${user.id}, '${user.name}', '${user.email}')">
                        Atualizar
                    </button>

                    <button onclick="excluirUsuario(${user.id})">
                        Excluir
                    </button>

                    <hr>
                </div>
            `;
        });

    } catch (error) {
        alert(error.message);
    }
});


// Atualizar usuário
async function atualizarUsuario(id, nomeAtual, emailAtual) {

    const nome = prompt("Novo nome:", nomeAtual);

    if (nome === null) {
        return;
    }

    const email = prompt("Novo e-mail:", emailAtual);

    if (email === null) {
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/operator/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({
                name: nome,
                email: email
            })
        });

        if (!response.ok) {
            throw new Error("Não foi possível atualizar o usuário.");
        }

        alert("Usuário atualizado com sucesso!");

        document.getElementById("buscarUsuarios").click();

    } catch (error) {
        alert(error.message);
    }
}


// Excluir usuário
async function excluirUsuario(id) {

    const confirmar = confirm("Tem certeza que deseja excluir este usuário?");

    if (!confirmar) {
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/admin/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error("Não foi possível excluir o usuário.");
        }

        alert("Usuário excluído com sucesso!");

        document.getElementById("buscarUsuarios").click();

    } catch (error) {
        alert(error.message);
    }
}


// Sair
document.getElementById("logout").addEventListener("click", () => {

    localStorage.removeItem("token");

    window.location.href = "../login/login.html";
});

//adc usuario

document.getElementById("adicionarUsuario").addEventListener("click", () => {

    conteudo.innerHTML = `
        <h2>Adicionar usuário</h2>

        <input type="text" id="novoNome" placeholder="Nome">

        <input type="email" id="novoEmail" placeholder="E-mail">

        <input type="password" id="novaSenha" placeholder="Senha">

        <select id="novaRole">
            <option value="CLIENT">CLIENT</option>
            <option value="OPERATOR">OPERATOR</option>
            <option value="ADMIN">ADMIN</option>
        </select>

        <button id="cadastrar">Cadastrar</button>
        <button id="voltar">Voltar</button>
    `;

    document.getElementById("cadastrar").addEventListener("click", async () => {

        const nome = document.getElementById("novoNome").value;
        const email = document.getElementById("novoEmail").value;
        const senha = document.getElementById("novaSenha").value;
        const role = document.getElementById("novaRole").value;

        try {

            const response = await fetch("http://localhost:8080/admin", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },

                body: JSON.stringify({
                    name: nome,
                    email: email,
                    senha: senha,
                    usersProfile: role
                })

            });

            if (!response.ok) {
                throw new Error("Não foi possível cadastrar o usuário.");
            }

            alert("Usuário cadastrado com sucesso!");

            document.getElementById("buscarUsuarios").click();

        } catch (error) {

            alert(error.message);

        }

    });

    document.getElementById("voltar").addEventListener("click", () => {
        document.getElementById("buscarUsuarios").click();
    });

});

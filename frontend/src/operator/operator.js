const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "../login/index.html";
}

let id;

document.getElementById("buscarDados").addEventListener("click", async () => {

    const email = document.getElementById("emailBusca").value;

    try{

        // basicamente tenta conectar e puxar os dados se der certo só mostra na tela

        const response = await fetch(`http://localhost:8080/operator/email/${email}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }); 

        if (!response.ok) {
            throw new Error(`Erro ${response.status}`);
        }

        const user = await response.json();

        document.getElementById("dados").innerHTML = `
        <p>ID: ${user.id}</p>
        <p>Nome: ${user.name}</p>
        <p>E-mail: ${user.email}</p>`;

        id = user.id;

        document.getElementById("nome").value = user.name;
        document.getElementById("email").value = user.email;

    } catch (error){
        console.error(error);
        alert(error.message);
    }

});

//atualizar o usuario
document.getElementById("atualizar").addEventListener("click", async () => { 
        const nome = document.getElementById("nome").value; 
        const email = document.getElementById("email").value; 

        try { 
            const response = await fetch(`http://localhost:8080/operator/${id}`, { 
                method: "PUT", 
                headers: { 
                    "Content-Type": "application/json", "Authorization": `Bearer ${token}` 
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
        
            } catch (error) { alert(error.message); 
                
            } 
        });



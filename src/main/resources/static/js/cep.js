document.addEventListener("DOMContentLoaded", () => {
    const cepInput = document.getElementById("cep");
    if (!cepInput) return;

    const status = document.getElementById("cep-status");
    const fields = {
        logradouro: document.getElementById("logradouro"),
        bairro: document.getElementById("bairro"),
        cidade: document.getElementById("cidade"),
        uf: document.getElementById("uf")
    };

    cepInput.addEventListener("blur", async () => {
        const cep = cepInput.value.replace(/\D/g, "");
        if (cep.length !== 8) return;

        status.textContent = "Consultando CEP...";
        try {
            const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
            if (!response.ok) throw new Error("Falha na consulta");
            const data = await response.json();
            if (data.erro) throw new Error("CEP não encontrado");

            fields.logradouro.value = data.logradouro ?? "";
            fields.bairro.value = data.bairro ?? "";
            fields.cidade.value = data.localidade ?? "";
            fields.uf.value = data.uf ?? "";
            status.textContent = "Endereço preenchido. Confira os dados.";
        } catch (error) {
            status.textContent = "Não foi possível consultar o CEP. Preencha o endereço manualmente.";
        }
    });
});

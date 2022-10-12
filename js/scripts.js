/*animar cards*/
let i = 0;
var contador = 0;
$(document).ready(function() {
    $('').hover(
        function() {
            $(this).animate({
                marginTop: "-=1%",
            }, 200);
        },



        function() {
            $(this).animate({
                marginTop: "0%",
            }, 200);
        });
});

/*Inserindo cards automaticamente */
function addNewCard() {
    i = 0;
    let imagensDeCachorro = ['img/dog1.jfif', 'img/dog2.jfif', 'img/dog3.jfif', 'img/dog4.jpg']
    let nomesDeCachorro = ['algustin', 'josefino', 'bernardo', 'clodomiro']
    if (contador == 0) {
        for (var i = 0; i < 11; i++) {
            let selecionaAleatorio = Math.floor(Math.random() * 4);
            let nomeCachorro = nomesDeCachorro[selecionaAleatorio];
            let imagemCachorro = imagensDeCachorro[selecionaAleatorio];
            $(".adotar-menu .row").append(`<div class="col-4-lg ml-3 pb-3">
    <div class="card" style="width:18rem">
        <img src="${imagemCachorro}" class="card-img-top" alt="cachorro para adoção" style="height: 200px">
        <div class="card-body">
            <h5 class="card-title">${nomeCachorro} derruba motoqueiro</h5>
            <p class="card-text">
                Nome:Zezinho
            </p>
            <a onclick="chamarTelaAdotar()" class="btn btn-primary">adotar</a>
        </div>
    </div>
</div>`);
        }

        contador = 1;
    }
}
let petSelecionado = [];



function chamarTelaAdotar() {



    let abrirPopUp = window.open('adotar.html', 'popUp',
        "width=350, height=255, top=100, left=110, scrollbars=no");

    return petSelecionado.push('teste');
}


function adotarPet() {
    $(".teste").append(`<div class="container-fluid">
    <div class="confirmar-adocao">
        <h1>${petSelecionado[0]}</h1>
        <button class="btn btn-success"> ADOTAR</button>
    </div>
</div>
`);
}
/*Cria objeto animal*/


function Animal(id, nome, idade, raca, comportamento, foto) {
    this.id = id,
        this.nome = nome,
        this.idade = idade,
        this.raca = raca;
    this.comportamento = comportamento,
        this.foto = foto
};
let animais = [];

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
        for (var i = 0; i < 12; i++) {
            let selecionaAleatorio = Math.floor(Math.random() * 4);
            let animal = new Animal(i, nomesDeCachorro[selecionaAleatorio], 1, 'lata', 'neutro', imagensDeCachorro[selecionaAleatorio]);
            let idCachorro = animal.id;
            let imagemCachorro = animal.foto;
            animais.push(animal);
            $(".adotar-menu .row").append(`<div class="col-4-lg ml-3 pb-3">
    <div class="card" style="width:18rem"id="${idCachorro}" >
        <img src="${imagemCachorro}" class="card-img-top" alt="cachorro para adoção" style="height: 200px">
        <div class="card-body">
            
            <p class="card-text" id="${idCachorro}">
                
            </p>
            <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal" onclick = "criaModal(${animal.id},'${animal.nome}',${animal.idade},'${animal.raca}','${animal.comportamento}','${animal.foto}')">Ver mais</button>
        </div>
    </div>
</div>  
`);

        }

        contador = 1;
        idCachorro = null;
    }
}

/*Cria modal*/

function criaModal(id, nome, idade, raca, comportamento, imagem) {
    console.log(id);
    console.log(nome);

    $(".adotar-menu .row").append(`<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
    
            <!-- Modal Header -->
            <div class="modal-header">
                <h2 class="modal-title font-weight-bold text-center" style = "color:blue">Adotar PET</h2>
                <button type="button" class="close" onClick="window.location.reload(false)" data-dismiss="modal">&times;</button>
            </div>
    
            <!-- Modal body -->
            <div class="modal-body">
            <img src="${imagem}" alt="c" style ="width:100%" height="250px">
            <h3 class="modal-title text-capitalize text-center">${nome}</h3>
            <p class="text-justify font-weight-bold">Temperamento:${comportamento}.</p>
            <p class="text-justify font-weight-bold">Raça:${raca}.</p>
            <p class="text-justify font-weight-bold">Idade:${idade}.</p>
            <p class="text-justify font-weight-bold">Este animal está disponivel para adoção na ong Amicão</p>
            </div>
    
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">Adotar</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onClick="window.location.reload(false)">Fechar</button>
            </div>
    
        </div>
    </div>
    
    </div>`);

}
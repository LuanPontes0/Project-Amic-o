/*Cria objeto animal*/


function Animal(id, nome, idade, raca, comportamento, foto) {
    this.id = id,
        this.nome = nome,
        this.idade = idade,
        this.raca = raca,
        this.comportamento = comportamento,
        this.foto = foto
};

function PetPerdido(id, nome, idade, raca, foto, nomeDoDono) {
    this.id = id,
        this.nome = nome,
        this.idade = idade,
        this.raca = raca,
        this.foto = foto,
        this.dono = nomeDoDono

}
let animais = [];

/*animar cards*/

let i = 0;
var contador = 0;

$(document).ready(function() {
    $('.card').hover(
        function() {
            $(this).animate({
                marginTop: "-=5%",
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
    /*preenchimento dos cards com informações estaticas!*/
    i = 0;
    let imagensDeCachorro = ['img/dog1.jfif', 'img/dog2.jfif', 'img/dog3.jfif', 'img/dog4.jpg']
    let nomesDeCachorro = ['algustin', 'josefino', 'bernardo', 'clodomiro']
    let petsPerdidos = ['corotin', 'claudinho', 'capetinha', 'zezinho']
    let fotosDisappear = ['img/gatoPerdido1.jpg', 'img/gatoPerdido2.jpg', 'img/caoPerdido1.jpg', 'img/caoPerdido2.jpg']
    let nomeDoDono = ['Amadeo', 'juliano', 'chris', 'tadeo'];
    if (contador == 0) {
        for (var i = 0; i < 12; i++) {
            let selecionaAleatorio = Math.floor(Math.random() * 4);
            let animal = new Animal(i, nomesDeCachorro[selecionaAleatorio], 1, 'lata', 'neutro', imagensDeCachorro[selecionaAleatorio]);
            let petPerdido = new PetPerdido(i, petsPerdidos[selecionaAleatorio], 1, 'lata', fotosDisappear[selecionaAleatorio], nomeDoDono[selecionaAleatorio]);
            let idCachorro = animal.id;
            let imagemCachorro = animal.foto;
            let idPerdido = petPerdido.id;
            let imagemPerdido = petPerdido.foto;
            animais.push(animal);
            console.log(petPerdido.dono)

            $(".adotar-menu .row").append(`<div class="col-4-lg ml-3 pb-3 mx-auto">
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
            $(".desaparecidos-menu .row").append(`<div class="col-4-lg ml-3 pb-3 mx-auto">
    <div class="card" style="width:18rem"id="${idPerdido}" >
        <img src="${imagemPerdido}" class="card-img-top" alt="cachorro para adoção" style="height: 200px">
        <div class="card-body">
            
            <p class="card-text" id="${idPerdido}">
                
            </p>
            <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModalPerdido" onclick = "modalPerdido(${petPerdido.id},'${petPerdido.nome}',${petPerdido.idade},'${petPerdido.raca}','${petPerdido.foto}','${petPerdido.dono}')">Ver mais</button>
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

    $(".adotar-menu .row").append(`<div class="modal fade mx-auto" id="myModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
    
            <!-- Modal Header -->
            <div class="modal-header">
                <h2 class="modal-title font-weight-bold text-center" style = "color:blue">Adotar PET</h2>
                <button type="button" class="close" onClick="document.getElementById('myModal').remove()" data-dismiss="modal">&times;</button>
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
                <button type="button" class="btn btn-success" data-dismiss="modal" onClick="criaFormulario(${id}),document.getElementById('myModal').remove()">Adotar</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onClick="document.getElementById('myModal').remove()">Fechar</button>
            </div>
    
        </div>
    </div>
    
    </div>`);
    contadorTeste = 1;

}

function criaFormulario(idCachorro) {
    let dogSelecionado = idCachorro;

    $('#myModal').on('hidden.bs.modal', function(e) {

        $("#myFormulario").modal('show');
    })
    $(".adotar-menu .row").append(`
    <div class="modal fade " id="myFormulario" data-backdrop="static">
                        <div class="modal-dialog">
                            <div class="modal-content">


                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h2 class="modal-title font-weight-bold text-center" style="color:blue">Formulário de adoção ${dogSelecionado} 
                                    </h2>
                                    <button type="button" class="close" onClick="document.getElementById('myFormulario').remove()" data-dismiss="modal">&times;</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                <form method="post" action=".">
        <div>
            <h4>Informações pessoais</h4>
            <div class="form-group">
                <label>Nome</label>
                <input class="form-control" type="text" id="nome" name="nome" placeholder="Seu nome completo" required>
            </div>
            <div class="form-group">
                <label>Cpf</label>
                <input class="form-control" type="text" id="cpf" name="cpf" placeholder="xxx.xxx.xxx-xx">
            </div>
            <div class="form-group">
                <label>E-mail</label>
                <input class="form-control" type="email" id="email" name="email" placeholder="E-mail">
            </div>
            <div class="form-group">
                <label>Data de nascimento</label>
                <input class="form-control" type="date" id="nascimento">
            </div>
            <div class="form-group">
                <label>telefone</label>
                <input class="form-control" type="tel" pattern="[0-9]{2}[0-9]{5}[0-9]{4}" placeholder="(xx)xxxxx-xxxx" id="telefone" name="telefone">
            </div>
            <h4>Endereço</h4>
            <div class="form-group">
                <label>Rua</label>
                <input class="form-control" type="text" placeholder="nome da sua rua" id="rua" name="rua">
                <label> Bairro</label>
                <input class="form-control" type="text" id="bairro" name="bairro" placeholder="nome do seu bairro">
                <label>Nº</label>
                <input class="form-control" type="text" id="numeroCasa" name="numeroCasa" placeholder="Número da sua casa">
            </div>
            <div class="form-group">
                <label>Cep</label>
                <input class="form-control" type="text" id="cep" name="cep" placeholder="cep" onblur="pesquisacep(this.value);">
                <label>cidade</label>
                <input class="form-control" type="text" id="cidade" name="cidade" placeholder="nome da sua cidade">
            </div>
            <div class="form-group">
                <label>Estado</label>
                <input class="form-control" type="text" id="uf" name="uf" placeholder="estado">
            </div>
            <h4>Motivo da adoção</h4>
            <div class="form-group">
                <textarea class="form-control" name="motivo" id="motivo" cols="50" rows="5" placeholder="Digite aqui o motivo de você estar querendo adotar"></textarea>
            </div>
        </div>
    </form>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-success">Adotar</button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" onClick="document.getElementById('myFormulario').remove()">Fechar</button>
                                </div>

                            </div>
                        </div>

                    </div>

    `);


}

/*Api via Cep*/
function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('rua').value = ("");
    document.getElementById('bairro').value = ("");
    document.getElementById('cidade').value = ("");
    document.getElementById('uf').value = ("");
    document.getElementById('ibge').value = ("");
}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('rua').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);

    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if (validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('rua').value = "...";
            document.getElementById('bairro').value = "...";
            document.getElementById('cidade').value = "...";
            document.getElementById('uf').value = "...";


            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
};
/*Função que vai inserir cards sobre os pets desaparecidos*/


/*Cria modal perdido*/
function modalPerdido(id, nome, idade, raca, imagem, dono) {


    console.log(id);
    console.log(nome);
    console.log(idade);
    console.log(dono);

    $(".desaparecidos-menu .row").append(`<div class="modal fade" id="myModalPerdido" data-backdrop="static" mx-auto">
    <div class="modal-dialog">
        <div class="modal-content">
    
            <!-- Modal Header -->
            <div class="modal-header">
                <h2 class="modal-title font-weight-bold text-center" style = "color:blue">Pet desaparecido</h2>
                <button type="button" class="close" onClick="document.getElementById('myModal').remove()" data-dismiss="modal">&times;</button>
            </div>
    
            <!-- Modal body -->
            <div class="modal-body">
            <img src="${imagem}" alt="c" style ="width:100%" height="250px">
            <h3 class="modal-title text-capitalize text-center">${nome}</h3>
            <p class="text-justify font-weight-bold">Nome do Dono:${dono}.</p>
            <p class="text-justify font-weight-bold">Raça:${raca}.</p>
            <p class="text-justify font-weight-bold">Idade:${idade}.</p>
            <p class="text-justify font-weight-bold">Este animal está desaparecido, caso o veja, por favor entrar em contato com o dono!</p>
            </div>
            
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal" onClick="document.getElementById('myModalPerdido').remove()">Fechar</button>
            </div>
    
        </div>
    </div>
    
    </div>`);
    contadorTeste = 1;

}

/*Botão da navbar*/
$(function() {
    $('#sidebarCollapse').on('click', function() {
        $('#sidebar,#content').toggleClass('active');
    });
});

window.onscroll = function() { scrollFunction() };

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtn").style.display = "block";
    } else {
        document.getElementById("myBtn").style.display = "none";
    }
}

function modalCookie() {
    $("#ExemploModalCentralizado").modal('show');
}

function modalEmail() {
    $("#MOdalSenha").modal('show');
}
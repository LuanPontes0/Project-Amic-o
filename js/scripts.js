/*animar cards*/
var contador = 0;
$(document).ready(function() {
    $('.col-4-lg').hover(
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
    if (contador == 0) {
        for (var i = 0; i < 20; i++) {
            $(".adotar-menu .row").append(`<div class="col-4-lg ml-3 pb-3">
    <div class="card" style="width:18rem">
        <img src="img/dog4.jpg" class="card-img-top" alt="cachorro para adoção">
        <div class="card-body">
            <h5 class="card-title">Zezim derruba motoqueiro</h5>
            <p class="card-text">
                Nome:Zezinho
            </p>
            <a href="#" class="btn btn-primary">adotar</a>
        </div>
    </div>
</div>`);
        }

        contador = 1;
    }
}
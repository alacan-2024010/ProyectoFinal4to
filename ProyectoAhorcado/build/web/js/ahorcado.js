let palabraElegida = "";
let espacios = [];
let letrasMalas = [];
let errores = 0;
let intentos = 6;

let tiempoRestante = 300;
let temporizador;
let juegoEnPausa = false;

const pista1 = document.getElementById("pistaUno");
const pista2 = document.getElementById("pistaDos");
const pista3 = document.getElementById("pistaTres");
const palabraOculta = document.getElementById("palabra-oculta");
const letrasIncorrectasEl = document.getElementById("letras-incorrectas");
const intentosEl = document.getElementById("intentos");
const imagen = document.getElementById("muñeco");
const cronometroEl = document.getElementById("cronometro");
const btnPausar = document.getElementById("btn-pausar");
const btnSalir = document.getElementById("btn-salir");
const img = document.getElementById("imagenResultado");
const modal = document.getElementById("modal");
const modalMensaje = document.getElementById("modal-mensaje");
const modalImagen = document.getElementById("modal-imagen");
const modalCerrar = document.getElementById("modal-cerrar");

async function cargarPalabraDesdeBD() {
    try {
        const respuesta = await fetch("ControladorAhorcado?action=obtenerPalabras", {
            headers: { "Accept": "application/json" }
        });
        if (!respuesta.ok) throw new Error("No se pudo obtener palabras del servidor");

        const data = await respuesta.json();
        if (!Array.isArray(data) || data.length === 0) {
            throw new Error("No hay palabras en la base de datos");
        }

        const obj = data[Math.floor(Math.random() * data.length)];
        palabraElegida = (obj.palabra || obj.Palabra || "").toUpperCase();
        if (!palabraElegida) throw new Error("El objeto recibido no contiene la palabra");

        espacios = Array(palabraElegida.length).fill("_");
        palabraOculta.textContent = espacios.join(" ");

        pista1.textContent = "Pista No.1: " + (obj.pista_uno|| obj.pista || "");
        pista2.textContent = "Pista No.2: " + (obj.pista_dos || "");
        pista3.textContent = "Pista No.3: " + (obj.pista_tres || "");
    } catch (err) {
        console.error(err);
        alert( err.message);
    }
}

async function comenzar() {
    if (!palabraElegida) {
        await cargarPalabraDesdeBD();

        if (!palabraElegida) {
            // fallback si no hay BD
            const palabraAleatoria = palabrasAhorcado[Math.floor(Math.random() * palabrasAhorcado.length)];
            palabraElegida = palabraAleatoria.palabra.toUpperCase();
            espacios = Array(palabraElegida.length).fill("_");
            palabraOculta.textContent = espacios.join(" ");
            pista1.textContent = "Pista No.1: " + palabraAleatoria.pistas[0];
            pista2.textContent = "Pista No.2: " + palabraAleatoria.pistas[1];
            pista3.textContent = "Pista No.3: " + palabraAleatoria.pistas[2];
        }

        tiempoRestante = 300;
        cronometroEl.textContent = tiempoRestante;
        iniciarCronometro();
    }
}

function reiniciar() {
    palabraElegida = "";
    espacios = [];
    letrasMalas = [];
    errores = 0;
    intentos = 6;

    palabraOculta.textContent = "";
    letrasIncorrectasEl.textContent = "";
    intentosEl.textContent = "Intentos restantes: 6";
    pista1.textContent = "";
    pista2.textContent = "";
    pista3.textContent = "";
    imagen.src = "img/Ahorcado.png";
    
     const contenedorImagenResultado = document.getElementById("imagenResultado");
    contenedorImagenResultado.innerHTML = "";
    contenedorImagenResultado.classList.remove("mostrar");

    tiempoRestante = 300;
    cronometroEl.textContent = tiempoRestante;
    juegoEnPausa = false;
    btnPausar.textContent = "Pausar";
    cerrarModal();
    comenzar();
    
}

function mostrarModal(mensaje, imagenSrc) {
    modalMensaje.textContent = mensaje;
    modalImagen.src = imagenSrc;
    modal.style.display = "block";
}

function cerrarModal() {
    modal.style.display = "none";
}

modalCerrar.addEventListener("click", cerrarModal);

function verificar() {
    const verificarLetra = document.getElementById("letra");
    const letra = verificarLetra.value.toUpperCase();
    verificarLetra.value = "";

    if (!letra || letrasMalas.includes(letra) || espacios.includes(letra)) return;

    if (palabraElegida.includes(letra)) {
        for (let i = 0; i < palabraElegida.length; i++) {
            if (palabraElegida[i] === letra) {
                espacios[i] = letra;
            }
        }
        palabraOculta.textContent = espacios.join(" ");
    } else {
        letrasMalas.push(letra);
        letrasIncorrectasEl.textContent = letrasMalas.join(", ");
        errores++;
        intentos--;
        intentosEl.textContent = `Intentos restantes: ${intentos}`;
        imagen.src = `img/Ahorcado${errores}.png`;
    }

    if (!espacios.includes("_")) {
        mostrarModal("¡Ganaste!", "img/Ganaste.webp");
    }

    if (errores === 6) {
        mostrarModal("¡Perdiste! La palabra era: " + palabraElegida, "img/Perdiste.png");
    }
}

function actualizarCronometro() {
    if (!juegoEnPausa && tiempoRestante > 0) {
        tiempoRestante--;
        cronometroEl.textContent = tiempoRestante;
    } else if (tiempoRestante <= 0) {
        clearInterval(temporizador);
         mostrarModal("¡Se acabó el tiempo! La palabra era: "  + palabraElegida, "img/Perdiste.png");
        reiniciar();
    }
}

function iniciarCronometro() {
    clearInterval(temporizador);
    temporizador = setInterval(actualizarCronometro, 1000);
}

function pausarJuego() {
    juegoEnPausa = !juegoEnPausa;
    btnPausar.textContent = juegoEnPausa ? "Reanudar" : "Pausar";
}

function salirJuego() {
    window.location.href = "index.jsp";
}



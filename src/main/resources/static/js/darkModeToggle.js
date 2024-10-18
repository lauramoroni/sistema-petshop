const botaoAlterarTema = document.getElementById("darkModeToggle");
const body = document.querySelector("body");

// o querySelector consulta um seletor (class, etc)
const trocarImagem = document.querySelector(".imagem-botao");

botaoAlterarTema.addEventListener("click", () => {
  // alterna a classe "dark-mode" no body
  body.classList.toggle("dark-mode");

  // salva a que ja tava no localStorage
  if (body.classList.contains("dark-mode")) {
    localStorage.setItem("theme", "dark");
    //trocar o sol pela lua
    trocarImagem.setAttribute("src", "/images/sun.png");
  } else {
    localStorage.setItem("theme", "light");
    //trocar a lua pelo sol
    trocarImagem.setAttribute("src", "/images/moon.png");
  }
});
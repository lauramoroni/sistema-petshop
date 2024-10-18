document.getElementById("download-pdf").addEventListener("click", function () {
    var element = document.getElementById("exame-info")
    var hemogramaId = document
      .querySelector("#hemograma-card h3 span")
      .textContent.trim()
    var opt = {
      margin: 0.5,
      filename: "exame_" + hemogramaId + ".pdf",
      html2canvas: {
        scale: 2,
        useCORS: true, 
        logging: true, 
        allowTaint: true, 
      },
      jsPDF: {
        unit: "in",
        format: "a4", 
        orientation: "portrait", 
      },
      pagebreak: { mode: ["avoid-all", "css", "legacy"] },
    }
    html2pdf().set(opt).from(element).save();
});
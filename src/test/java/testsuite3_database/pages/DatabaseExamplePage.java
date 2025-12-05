package testsuite3_database.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;

/**
 * Page Object para a aplicação Database Example (Vaadin)
 * URL: https://vaadin-database-example.demo.vaadin.com/
 */
public class DatabaseExamplePage {

    // Seletor para o campo de pesquisa (Search Input)
    // O Vaadin usa elementos complexos; este seletor é um bom ponto de partida.
    public SelenideElement filterField = $("vaadin-text-field[label='Search'] input");

    // Seletor para a grelha de filmes (Vaadin Grid)
    // O componente 'vaadin-grid' contém todos os resultados.
    public SelenideElement moviesGrid = $("vaadin-grid");

    // Seletor para a primeira linha da grelha (Grid Row)
    // Pode ser usado para validar a presença de um resultado.
    // Usamos um seletor CSS mais específico para o primeiro item na grelha.
    public SelenideElement firstGridRow = moviesGrid.find("vaadin-grid-row");

    /**
     * Método de Ação: Filtra os filmes pelo título.
     * @param title O título do filme a pesquisar.
     */
    public void filterByTitle(String title) {
        filterField.setValue(title);
    }

    /**
     * Método de Verificação: Obtém o texto do título na primeira linha da grelha.
     * @return O texto do título.
     */
    public String getFirstMovieTitle() {
        // Assume que o título está no primeiro elemento 'cell-content' da linha.
        return firstGridRow.find("vaadin-grid-cell-content", 0).text();
    }
}
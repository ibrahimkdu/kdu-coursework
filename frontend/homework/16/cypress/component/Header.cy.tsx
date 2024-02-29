import { Provider } from "react-redux";
import { store } from "../../src/redux/store";
import Header from "../../src/Header";
describe("Header component", () => {
  it("should display title and search input", () => {
    cy.viewport(700, 700);
    cy.mount(
        <Provider store={store}>
          <Header />
        </Provider>
    );
    cy.get(".title").should("exist");
    cy.get(".search-bar input").should("exist");
  });
});

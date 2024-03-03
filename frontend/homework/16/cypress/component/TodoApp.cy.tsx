import { Provider } from "react-redux";
import { store } from "../../src/redux/store";
import TodoApp from "../../src/TodoApp";

describe("TodoApp component", () => {
  it("should display header, add item section, and todo items section", () => {
    cy.viewport(700, 700);
    cy.mount(
      <Provider store={store}>
        <TodoApp />
      </Provider>
    );
    cy.get(".header").should("exist");
    cy.get(".add-item").should("exist");
    cy.get(".todo-items").should("exist");
  });
});

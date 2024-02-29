import { Provider } from "react-redux";
import { store } from "../../src/redux/store";
import TodoItem from "../../src/TodoItem";

describe("TodoItem component", () => {
  it("should display todo item and delete button", () => {
    cy.viewport(700, 700);
    cy.mount(
      <Provider store={store}>
        <TodoItem id={1} name="Test Item" />
      </Provider>
    );
    cy.get(".todo-item").should("exist");
    cy.get(".delete").should("exist");
  });
});

describe("TodoList Application", () => {
  it("should display header", () => {
    cy.visit("http://localhost:5173/");
    cy.get(".header").should("exist");
  });

  it("should add new item", () => {
    const newItem = "New Todo Item";
    cy.visit("http://localhost:5173/");
    cy.get(".input-field").type(newItem);
    cy.get(".submit-button").click();
    cy.contains(".todo-item-li", newItem).should("exist");
  });

  it("should delete an item", () => {
    cy.visit("http://localhost:5173/");
    cy.get(".delete").first().click();
    cy.contains(".todo-item-li").should("not.exist");
  });

  it("should filter items by search term", () => {
    const searchTerm = "Task 1";
    cy.visit("http://localhost:5173/");
    cy.get(".search-bar input").type(searchTerm);
    cy.get(".todo-list .todo-item-li", { timeout: 15000 })
      .each(($el) => {
        cy.wrap($el).should("contain.text", searchTerm);
      })
      .should("have.length.gt", 0);
  });
});

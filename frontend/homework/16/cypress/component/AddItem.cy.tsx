
import { Provider } from 'react-redux';
import {store} from '../../src/redux/store';
import AddItem from '../../src/AddItem';

describe('AddItem component', () => {
    it('should display input field and submit button', () => {
      cy.viewport(700, 700);
      cy.mount(
          <Provider store={store}>
            <AddItem />
          </Provider>
      );
      cy.get('.input-field').should('exist');
      cy.get('.submit-button').should('exist');
    });
  });
  
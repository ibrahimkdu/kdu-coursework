import { describe, test } from 'vitest';
import { screen, fireEvent, render } from '@testing-library/react';
import { Provider } from 'react-redux';
import { store } from './redux/store';
import TodoApp from './TodoApp';

describe("TodoApp Component", () => {
  test('renders search box and changes value correctly', () => {
    render(
      <Provider store={store}>
        <TodoApp />
      </Provider>
    );
    expect(screen.getByTestId('search-box')).toBeInTheDocument();

    const searchbox = screen.getByTestId('search-box');
    fireEvent.change(searchbox, { target: { value: 'y' } });
    expect(searchbox.value).toBe('y');
  });

  test('renders add item component', () => {
    render(
      <Provider store={store}>
        <TodoApp />
      </Provider>
    );
    expect(screen.getByTestId('add-item')).toBeInTheDocument();
  });
  test('renders list of todo items', () => {
    render(
      <Provider store={store}>
        <TodoApp />
      </Provider>
    );
    expect(screen.getByTestId('todo-items')).toBeInTheDocument();
  });

});


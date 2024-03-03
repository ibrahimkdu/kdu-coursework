import { Provider } from 'react-redux';
import { store } from './redux/store';
import TodoApp from './TodoApp';

function App() {
  return (
    <div>
      <Provider store={store}>
        <TodoApp />
      </Provider>
    </div>
  );
}

export default App;

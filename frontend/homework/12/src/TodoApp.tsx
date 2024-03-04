import React, { useState } from 'react';
import './Todoapp.scss';
import Header from './Header';
import AddItem from './AddItem';
import TodoList from './TodoList'; // Import TodoList component
import { TodoProvider } from './TodoContext';

function TodoApp() {
  const [searchTerm, setSearchTerm] = useState<string>('');
  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  return (
    <TodoProvider>
      <div className="todo-app">
        <Header searchTerm={searchTerm} onSearchChange={handleSearch} />
        <div className="content">
          <div className="add-item">
            <AddItem />
          </div>
          <div className="todo-items">
            <TodoList searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
          </div>
        </div>
      </div>
    </TodoProvider>
  );
}

export default TodoApp;

// import React, { useState } from 'react';
// import './Todoapp.scss';
// import Header from './Header';
// import AddItem from './AddItem';
// import TodoList from './TodoList'; // Import TodoList component
// import { TodoProvider } from './TodoContext';

// function TodoApp() {
//   const [searchTerm, setSearchTerm] = useState<string>('');
//   const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
//     setSearchTerm(event.target.value);
//   };

//   return (
//     <TodoProvider>
//       <div className="todo-app">
//         <Header searchTerm={searchTerm} onSearchChange={handleSearch} />
//         <div className="content">
//           <div className="add-item">
//             <AddItem />
//           </div>
//           <div className="todo-items">
//             <TodoList searchTerm={searchTerm} onSearchChange={handleSearch}  />
//           </div>
//         </div>
//       </div>
//     </TodoProvider>
//   );
// }

// export default TodoApp;

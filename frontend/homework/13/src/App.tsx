import React from 'react';
import { BrowserRouter,Routes,Route} from 'react-router-dom';
import ProductList from './components/ProductList';
import ProductDetail from './components/ProductDetail';
import { ProductProvider } from './context/ProductContext';

function App() {
  return (
    <ProductProvider>
    <BrowserRouter>
    <Routes>
     <Route path="/" element={<ProductList />} />
     <Route path="/product/:id" element={<ProductDetail />} />
     </Routes>
    </BrowserRouter>
    </ProductProvider>
  );
}

export default App;

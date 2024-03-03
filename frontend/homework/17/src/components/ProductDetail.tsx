import React from 'react';
import { useParams} from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import { MobileApi } from '../types/MobileApi';

const ProductDetail: React.FC = () => {
  const { id } = useParams<{ id?: string }>();



  if (!id) {
    return <div>Product ID not provided</div>;
  }

  const selectedProduct = useSelector(
    (state: RootState) => state.product.items.find((item) => item.id === +(id || 0))
  ) as MobileApi | undefined;

  if (!selectedProduct) {
    
  
    return null; 
  }

  return (
    <div>
      <h2>Product Detail</h2>
      <p>ID: {selectedProduct.id}</p>
      <p>Title: {selectedProduct.title}</p>
      <p>Price: ${selectedProduct.price}</p>
    </div>
  );
};

export default ProductDetail;


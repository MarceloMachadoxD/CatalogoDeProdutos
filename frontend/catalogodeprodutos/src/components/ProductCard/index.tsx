import ProductPrice from 'components/ProductPrice';
import { Product } from 'types/product';
import './styles.css';

type Props = {
  product: Product;
}


const ProductCard = ({ product } : Props) => {
  return (
    <>
      <div className='base-card product-card'>
        <div className='card-top-container'>
          <img src={product.imgURL} alt={product.name} />
        </div>
        <div className='card-bottom-container'>
            <h6>{product.name}</h6>
            <ProductPrice price={product.price} />
        </div>
      </div>
    </>
  );
};

export default ProductCard;

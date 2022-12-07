import Product from 'assets/images/product.png';
import ProductPrice from 'components/ProductPrice';
import './styles.css';

const ProductCard = () => {
  return (
    <>
      <div className='base-card product-card'>
        <div className='card-top-container'>
          <img src={Product} alt="" />
        </div>
        <div className='card-bottom-container'>
            <h6>Nome do Produto</h6>
            <ProductPrice />
        </div>
      </div>
    </>
  );
};

export default ProductCard;

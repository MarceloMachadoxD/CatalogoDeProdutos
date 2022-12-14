import ProductCard from 'components/ProductCard';
import { Link } from 'react-router-dom';
import { Product } from 'types/product';
import './styles.css';

const product: Product = {
  id: 1,
  name: 'The Lord of the Rings',
  description:
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
  price: 90.5,
  imgURL:
    'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg',
  date: '2020-07-13T20:50:07.123450Z',
  categories: [
    {
      id: 2,
      name: 'Electronics',
    },
  ],
};

const Catalog = () => {
  return (
    <>
      <div className="container my-4 catalog-container">
        <div className='row catalog-title-container'>
          <h1>Catalogo de Produtos</h1>

        </div>
        <div className="row">
          <div className="col-sm-6 col-lg-4 col-xl-3">
            <Link to="/products/1">
            <ProductCard product={product} />
            </Link>
          </div>
        </div>
      </div>
    </>
  );
};

export default Catalog;

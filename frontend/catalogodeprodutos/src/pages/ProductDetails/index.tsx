import './styles.css';

import { ReactComponent as ArrowIcon } from 'assets/images/arrow-image.svg';
import ProductPrice from 'components/ProductPrice';
import { Link } from 'react-router-dom';

const ProductDetails = () => {
  return (
    <>
      <div className="product-details-container">
        <div className="base-card product-detais-card">
          <Link to="/products">
            <div className="goback-container">
              <ArrowIcon />
              <h2>VOLTAR</h2>
            </div>
          </Link>
          <div className="row">
            <div className="col-xl-6">
              <div className="img-container">
                <img
                  src="https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg"
                  alt="nome do produto"
                />
              </div>
              <div className="name-price-container">
                <h1>Nome do produto</h1>
                <ProductPrice price={123.4} />
              </div>
            </div>
            <div className="col-xl-6">
              <div className="description-container">
                <h2>DESCRIÇÃO DO PRODUTO</h2>
                <p>
                  UIAHUIahIUAHIUH adhoashdlahsd AODHAOUHDUADKJASBDKAJSBD
                  akjasdkjabhskjdbaksdb
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ProductDetails;

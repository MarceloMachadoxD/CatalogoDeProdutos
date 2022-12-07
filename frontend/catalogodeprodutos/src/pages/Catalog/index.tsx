import Navbar from 'components/Navbar';
import ProductCard from 'components/ProductCard';
import './styles.css';

const Catalog = () => {
  return (
    <>
      <h1>Tela de Catalog</h1>
      <div className="container my-4">
        <div className="row">
          <div className="col-sm-6 col-lg-4 col-xl-3 ">
            <ProductCard />
          </div>
          <div className="col-sm-6 col-lg-4 col-xl-3 ">
            <ProductCard />
          </div>
          <div className="col-sm-6 col-lg-4 col-xl-3 ">
            <ProductCard />
          </div>
          <div className="col-sm-6 col-lg-4 col-xl-3">
            <ProductCard />
          </div>
          <div className="col-sm-6 col-lg-4 col-xl-3 ">
            <ProductCard />
          </div>
          <div className="col-sm-6 col-lg-4 col-xl-3 ">
            <ProductCard />
          </div>
        </div>
      </div>
    </>
  );
};

export default Catalog;

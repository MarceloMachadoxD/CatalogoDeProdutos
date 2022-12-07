import './styles.css';

import { ReactComponent as ArrowImage } from 'assets/images/arrow-image.svg';

const ButtonIcon = () => {
  return (
    <>
      <div className="btn-container">
        <button className="btn btn-primary">
          <h6>Inicie agora sua busca</h6>
        </button>

        <div className="btn-icon-container">
          <ArrowImage />
        </div>
      </div>
    </>
  );
};

export default ButtonIcon;

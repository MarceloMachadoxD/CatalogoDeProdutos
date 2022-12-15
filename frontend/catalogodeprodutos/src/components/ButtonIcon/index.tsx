import './styles.css';

import { ReactComponent as ArrowImage } from 'assets/images/arrow-image.svg';

type Props = {
  text: string
}

const ButtonIcon = ({text} : Props) => {
  return (
    <>
      <div className="btn-container">
        <button className="btn-text btn btn-primary">
          <h6>{text}</h6>
        </button>

        <div className="btn-icon-container">
          <ArrowImage />
        </div>
      </div>
    </>
  );
};

export default ButtonIcon;

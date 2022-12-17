import './styles.css';
import Navbar from './Navbar';

function Admin() {
  return (
    <>
      <div className="admin-container">
        <Navbar />
        <div className="admin-content">
          <h1>Conteudo</h1>
        </div>
      </div>
    </>
  );
}

export default Admin;

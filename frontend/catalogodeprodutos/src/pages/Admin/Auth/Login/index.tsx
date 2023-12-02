import { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import ButtonIcon from 'components/ButtonIcon';
import { useForm } from 'react-hook-form';
import { getAuthData, requestBackendLogin, saveAuthData } from 'util/requests';

import './styles.css';

type FormData = {
  username: string;
  password: string;
};

const Login = () => {
  const [errorMessage, setErrorMessage] = useState<string>();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>();
  const [isError, setIsError] = useState(false);

  const history = useHistory();

  const onSubmit = (formData: FormData) => {
    requestBackendLogin(formData)
      .then((response) => {
        saveAuthData(response.data);
        history.push('/admin');
        setIsError(false);
      })
      .catch((error) => {
        let errorMessage = 'Erro desconhecido';
        if (error?.response?.data?.error_description !== undefined) {
          setErrorMessage(error?.response?.data?.error_description);
        }
        setErrorMessage(errorMessage);
        setIsError(true);
      });
  };
  return (
    <div className="base-card login-card">
      <h1>LOGIN</h1>
      {isError && (
        <div className="alert alert-danger">
          <p className="mb-0">{errorMessage}</p>
        </div>
      )}
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-4">
          <input
            {...register('username', {
              required: 'Campo Obrigatório',
              pattern: {
                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                message: 'Email inválido',
              },
            })}
            type="text"
            className={`form-control base-input ${
              errors.username ? 'is-invalid' : ''
            }`}
            placeholder="Email"
            name="username"
          />
          <div className="invalid-feedback d-block">
            {errors.username?.message}
          </div>
        </div>
        <div className="mb-2">
          <input
            {...register('password', { required: 'Campo Obrigatório' })}
            type="password"
            className={`form-control base-input ${
              errors.password ? 'is-invalid' : ''
            }`}
            placeholder="Senha"
            name="password"
          />
          <div className="invalid-feedback d-block">
            {errors.password?.message}
          </div>
        </div>
        <Link to="/admin/auth/recover" className="login-link-recover">
          Esqueci a senha
        </Link>
        <div className="login-submit">
          <ButtonIcon text="Fazer login" />
        </div>
        <div className="signup-container">
          <span className="not-registered">Não tem Cadastro?</span>
          <Link to="/admin/auth/register" className="login-link-register">
            CADASTRAR
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Login;

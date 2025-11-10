CREATE TABLE IF NOT EXISTS cuenta (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    account_type VARCHAR(20) NOT NULL CHECK (tipo_cuenta IN ('AHORRO', 'CORRIENTE')),
    initial_balance DECIMAL(15,2) NOT NULL CHECK (saldo_inicial >= 0),
    actual_balance DECIMAL(15,2) NOT NULL,
    state BOOLEAN DEFAULT true,
    cliente_id VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla movimiento
CREATE TABLE IF NOT EXISTS movimiento (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    transaction_type VARCHAR(20) NOT NULL CHECK (tipo_movimiento IN ('DEPOSITO', 'RETIRO')),
    value DECIMAL(15,2) NOT NULL CHECK (valor > 0),
    balance DECIMAL(15,2) NOT NULL,
    account_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id) ON DELETE CASCADE
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_account_number ON cuenta(account_number);
CREATE INDEX idx_account_client ON cuenta(client_id);
CREATE INDEX idx_account_state ON cuenta(state);
CREATE INDEX idx_transaction_account ON movimiento(account_id);
CREATE INDEX idx_transaction_date ON movimiento(date);
CREATE INDEX idx_transaction_account_date ON movimiento(account_id, date);

-- Datos de prueba iniciales
INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_actual, estado, cliente_id)
VALUES
    ('478758', 'AHORRO', 2000.00, 2000.00, true, 'joseLema'),
    ('225487', 'CORRIENTE', 100.00, 100.00, true, 'marianelaMontalvo'),
    ('495878', 'AHORRO', 0.00, 0.00, true, 'juanOsorio'),
    ('496825', 'AHORRO', 540.00, 540.00, true, 'marianelaMontalvo'),
    ('585545', 'CORRIENTE', 1000.00, 1000.00, true, 'joseLema')
    ON CONFLICT (numero_cuenta) DO NOTHING;
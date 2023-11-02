import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

# Загрузка и подготовка данных
(x_train, y_train), (x_test, y_test) = keras.datasets.mnist.load_data()
x_train = x_train.reshape(-1, 28*28).astype("float32") / 255.0
x_test = x_test.reshape(-1, 28*28).astype("float32") / 255.0

# Создание модели с использованием последовательного API
model = keras.Sequential([
    keras.Input(shape=(28*28)),
    layers.Dense(512, activation='relu'),
    layers.Dense(256, activation='relu'),
    layers.Dense(10, activation='softmax')
])

# Настройка параметров обучения
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Обучение модели
model.fit(x_train, y_train, batch_size=32, epochs=5, verbose=2)

# Оценка модели
model.evaluate(x_test, y_test, batch_size=32, verbose=2)

# Сохранение модели
model.save("image_classification_model")

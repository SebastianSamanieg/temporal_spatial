import csv
from faker import Faker
import random
import time



fake = Faker()


def generate_data():
    data = []
    for _ in range(500**2):
        username = fake.user_name()
        birthdate = fake.date_of_birth(
            minimum_age=18,
            maximum_age=70).strftime('%Y-%m-%d')
        income = round(random.uniform(1000, 10000), 2)
        debt = round(random.uniform(0, 5000), 2)
        sex = random.choice(['Male', 'Female'])
        num_children = random.randint(0, 5)
        country = fake.country()
        data.append([
            username,
            birthdate,
            income,
            debt,
            sex,
            num_children,
            country])

    return data


def save_to_csv(data, filename):
    with open(filename, mode='w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([
            'Username',
            'Birthdate',
            'Income',
            'Debt',
            'Sex',
            'Number of Children',
            'Country'])
        writer.writerows(data)


if __name__ == "__main__":
    # Registra el tiempo de inicio
    inicio = time.time()
    generated_data = generate_data()
    save_to_csv(generated_data, 'dummy_data.csv')
    print("Data generation and CSV creation complete.")
    # Registra el tiempo de finalización
    fin = time.time()
    # Calcula la duración de la ejecución
    duracion = fin - inicio
    print(f"Tiempo de ejecución: {duracion:.6f} segundos")

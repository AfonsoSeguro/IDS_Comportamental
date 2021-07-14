import numpy as np
from tensorflow import keras
from tensorflow.keras import layers
from random import random


class Agente:
    def __init__(self, createModel, mutation_rate = 0.2, model = None):
        self.createModel = createModel
        if(model == None): self.model = createModel()
        else: self.model = model
        self.mutation_rate = mutation_rate
        self.fitness = 0

    def getFitness(self):
        return self.fitness

    def setFitness(self, fitness):
        self.fitness = fitness

    def calculateFitness(self, y_true, y_pred):
        losses = keras.losses.sparse_categorical_crossentropy(y_true, y_pred).numpy()
        total = 0
        for loss in losses: total += loss
        self.fitness = total / len(losses)
        return self.fitness

    def resetFitness(self):
        self.fitness = 0

    def predict(self, inp):
        return self.model.predict(inp)

    def mutate(self):
        for i in range(len(self.model.layers)):
            pesos = self.model.layers[i].get_weights()
            for j in range(len(pesos)):
                for k in range(len(pesos[j])):
                    if(random() < self.mutation_rate):
                        pesos[j][k] *= (random() + 1) * 0.5
            self.model.layers[i].set_weights(pesos)

    def save(self, name):
        self.model.save(name + ".h5")

    def load(self, name):
        self.model = keras.models.load_model(name + ".h5")

    def copy(self):
        copy = self.createModel()
        copy.set_weights(self.model.get_weights())
        return Agente(self.createModel, self.mutation_rate, self.model)
import numpy
import matplotlib.pyplot as plt

m = 1
a = np.linspace(0,10,101)
F1 = m*a

dt = 10/len(a)
v = np.zeros(len(a))
F2 = np.zeros(len(a))

for i in range(0,len(a)):
    v[i] = m* dt*a[i]
    F2[i] = m + v[i]


plt.plot(a,F1)
plt.plot(v,F2)
plt.show()

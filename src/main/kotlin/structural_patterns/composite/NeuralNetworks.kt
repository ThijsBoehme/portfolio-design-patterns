package structural_patterns.composite

import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

interface SomeNeurons: Iterable<Neuron> {
    fun connectTo(otherNeurons: SomeNeurons) {
        if (this == otherNeurons) return

        for (from in this) {
            for (to in otherNeurons) {
                from.outgoing.add(to)
                to.incoming.add(from)
            }
        }
    }
}

class Neuron: SomeNeurons {
    var incoming = ArrayList<Neuron>()
    var outgoing = ArrayList<Neuron>()

    // BAD: Need to create 4 versions
    // neuron-neuron, neuron-layer, layer-neuron, layer-layer
    //
    // fun connectTo(neuron: Neuron) {
    //     outgoing.add(neuron)
    //     neuron.incoming.add(this)
    // }

    override fun forEach(action: Consumer<in Neuron>?) {
        action?.accept(this)
    }

    override fun iterator(): Iterator<Neuron> {
        return Collections.singleton(this).iterator()
    }

    override fun spliterator(): Spliterator<Neuron> {
        return Collections.singleton(this).spliterator()
    }
}

class NeuronLayer: ArrayList<Neuron>(), SomeNeurons

fun main() {
    val neuron1 = Neuron()
    val neuron2 = Neuron()
    val layer1 = NeuronLayer()
    val layer2 = NeuronLayer()

    neuron1.connectTo(neuron2)
    neuron1.connectTo(layer1)
    layer1.connectTo(neuron1)
    layer1.connectTo(layer2)
}

<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-select
    multiple
    solo
    :label=label
    :item-text=mainProperty
    v-model="elements"
    :value="value"
    :items=dataArray
    :return-object="true"
    clearable
    @input="handleInput"
  >
    <template v-slot:selection="{ item, index }">
        <span v-if="elements.length > 1 && index === 0">
          {{ label }} ({{elements.length}})
        </span>
      <span
        v-if="elements.length === 1"
      >{{slicedName(item[mainProperty])}}</span>
    </template>
  </v-select>
</template>

<script>
export default {
  name: 'FilterSelectorComponent',
  props: {
    label: {
      type: String,
      required: true
    },
    mainProperty: {
      type: String,
      required: true
    },
    dataArray: {
      type: Array,
      required: true
    },
    value: Array
  },
  data () {
    return {
      elements: this.value
    }
  },
  methods: {
    handleInput (e) {
      console.log('here')
      this.$emit('input', this.elements)
    },
    slicedName (value) {
      if (value.length > 11) {
        return value.substring(0, 11) + '...'
      }
      return value
    }
  }
}
</script>

<style scoped>

</style>

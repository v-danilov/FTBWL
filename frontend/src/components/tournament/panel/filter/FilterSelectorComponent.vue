<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-select
    multiple
    :label=label
    :item-text=mainProperty
    v-model="elements"
    :value="value"
    :items=dataArray
    :return-object="true"
    clearable
    @input="handleInput"
  >
    <template v-slot:prepend-item>
      <v-list-tile
        ripple
        @click="toggle"
      >
        <v-list-tile-action>
          <v-icon :color="elements && elements.length > 0 ? 'secondary' : ''">{{ icon }}</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-list-tile-title>Выбрать всё</v-list-tile-title>
        </v-list-tile-content>
      </v-list-tile>
      <v-divider class="mt-2"></v-divider>
    </template>
    <template v-slot:selection="{ item, index }">
        <span v-if="elements.length > 1 && index === 0">
          {{placeHolder}}
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
    shortLabel: {
      type: String,
      required: false
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
      this.$emit('input', this.elements)
    },
    slicedName (value) {
      if (value.length > 11) {
        return value.substring(0, 11) + '...'
      }
      return value
    },
    resetSelected () {
      this.elements = []
    },
    toggle () {
      this.$nextTick(() => {
        if (this.selectAllElements) {
          this.elements = []
        } else {
          this.elements = this.dataArray.slice()
        }
      })
    }
  },
  computed: {
    placeHolder () {
      let elementsCounter = ' (' + this.elements.length + ')'
      let text = this.label + elementsCounter
      if (text.length > 11 && !!this.shortLabel) { return this.shortLabel + elementsCounter }
      return text
    },
    selectAllElements () {
      return this.elements && this.elements.length === this.dataArray.length
    },
    selectSomeElements () {
      return this.elements && this.elements.length && !this.selectAllElements
    },
    icon () {
      if (this.selectAllElements) return 'check_box'
      if (this.selectSomeElements) return 'indeterminate_check_box'
      return 'check_box_outline_blank'
    }
  }
}
</script>

<style scoped>
span {
  color: black;
  font-weight: normal;
  word-wrap: unset;
}
</style>

<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-select
    :label="label"
    :item-text="mainProperty"
    v-model="elements"
    :value="value"
    :items="dataArray"
    :return-object="true"
    @input="handleInput"
    multiple
    clearable
  >
    <template v-slot:prepend-item>
      <v-list-item
        @click="toggle"
        ripple
      >
        <v-list-item-action>
          <v-icon :color="elements && elements.length > 0 ? 'secondary' : ''">{{ icon }}</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Выбрать всё</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider class="mt-2" />
    </template>
    <template v-slot:selection="{ item, index }">
      <span v-if="elements.length > 1 && index === 0">
        {{ placeHolder }}
      </span>
      <span
        v-if="elements.length === 1"
      >{{ slicedName(item[mainProperty]) }}</span>
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
  computed: {
    placeHolder () {
      const elementsCounter = ' (' + this.elements.length + ')'
      const text = this.label + elementsCounter
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

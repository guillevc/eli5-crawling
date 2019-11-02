<template>
  <span :class="['tag', category.tagClass]">
    <font-awesome-icon :icon="category.icon"></font-awesome-icon>
    <span class="category-name">{{category.name}}</span>
  </span>
</template>

<script>
export default {
  name: 'CategoryTag',
  props: {
    category: {
      type: Object,
      required: true
    }
  },

};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';

.category-name {
  margin-left: .5rem;
}
</style>

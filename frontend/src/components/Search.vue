<template>
  <div class="section">
    <div class="container">
      <div class="wrapper">
        <SearchInput
          class="search-input"
          :searchQuery="searchQuery"
          :category="category"
          @submit="onSearchSubmit">
        </SearchInput>
      </div>
      <section class="results">
        <p class="results-summary">Found 14 matches for: "{{searchQuery}}" <span v-if="category !== ''">in {{categories[category].name}}</span></p>
        <ol class="answers">
          <li>
            <p><category-tag :category="categories['mathematics']"></category-tag><small class="question">ELI5: What is fuzzy logic and how is it that it's found in missiles and rice cookers?</small></p>
            <p>Traditional logic only knows two states: True or False. Is the rice cooker hot enough? If yes, turn off the heating element, and if not, turn it on.
Fuzzy logic can also express to what extent a statement is true on a sliding scale. Is the rice cooker hot enough? No, but it's close, so turn down the heating element a bit.
In a lot of control systems that extra information can be beneficial because it allows the control logic to make better decisions. In the rice cooker for example the heating element has a certain amount of thermal mass, and even if you turn it off, it will remain hot for a few moments and continue to heat the rice even without power going to it.
That means you will overshoot the desired temperature by a bit if you keep it powered at 100% until you reach the target temperature. And if you keep it at 0% until the temperature falls below the target, the heating element will need a few moments to heat up when you do turn it on, so there's going to be undershoot as well. So instead of maintaining a constant 60°C to keep the rice warm, the actual temperature may oscillate between 57°C and 63°C. If the controller is smart enough to reduce the output of the heating element when it approaches the target temperature, there's not going to be as much overshoot.       </p>
          </li>
          <li>
            <p><category-tag :category="categories['technology']"></category-tag> <small class="question">ELI5: How did we get to the point where laptops and smartphones are in the same price range?</small></p>
            <p>Demand for fancier phones with bigger screens and better cameras = higher cost, more people willing to pay for them.
That being said, this is only true if you're comparing very specific subsets of price ranges. Laptops are still notably more expensive. Unless you're say, comparing a middle of the road phone to a low end laptop which isn't really a fair comparison.
Looking at only big namebrands here to remove extreme outliers.
A low-end smartphone (Samsung Galaxy A10) might be $120. A low-end laptop (Asus Chromebook C523) might be $240.
A top-end smartphone (iPhone XS Max) might be $1500. A top-end laptop might be anywhere from $2500 for a more general purpose high-performer (High end macbook pro) or $3200+ for a high-end gaming laptop with a high-end display (ASUS ROG Zephyrus S GX701)
            </p>
          </li>
          <li>
            <p><category-tag :category="categories['physics']"></category-tag><small class="question">ELI5: What is fuzzy logic and how is it that it's found in missiles and rice cookers?</small></p>
            <p>Traditional logic only knows two states: True or False. Is the rice cooker hot enough? If yes, turn off the heating element, and if not, turn it on.
Fuzzy logic can also express to what extent a statement is true on a sliding scale. Is the rice cooker hot enough? No, but it's close, so turn down the heating element a bit.
In a lot of control systems that extra information can be beneficial because it allows the control logic to make better decisions. In the rice cooker for example the heating element has a certain amount of thermal mass, and even if you turn it off, it will remain hot for a few moments and continue to heat the rice even without power going to it.
That means you will overshoot the desired temperature by a bit if you keep it powered at 100% until you reach the target temperature. And if you keep it at 0% until the temperature falls below the target, the heating element will need a few moments to heat up when you do turn it on, so there's going to be undershoot as well. So instead of maintaining a constant 60°C to keep the rice warm, the actual temperature may oscillate between 57°C and 63°C. If the controller is smart enough to reduce the output of the heating element when it approaches the target temperature, there's not going to be as much overshoot.       </p>
          </li>
        </ol>
      </section>
    </div>
  </div>
</template>

<script>
import CategoryTag from './CategoryTag.vue';
import SearchInput from './SearchInput.vue';
import categories from '../model/categories';

export default {
  name: 'Search',
  props: {
    searchQuery: {
      type: String,
      default: ''
    },
    category: {
      type: String,
      default: '',
      validator: value => [...Object.keys(categories), ''].includes(value)
    }
  },
  data: () => ({
    categories
  }),
  methods: {
    onSearchSubmit(q, c) {
      this.$router.push({ name: 'search', query: { q, c } });
    }
  },
  components: {
    CategoryTag,
    SearchInput
  },
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';

.results {
  margin-top: 1.5rem;
}

.results-summary {
  margin-bottom: 1rem;
}

.search-input {
  max-width: 60em;
  margin: 0 auto;
}

.answers {
  li {
    list-style-type: none;
    padding: 1rem;
    border-radius: 4px;
    background-color: white;
    box-shadow: 0px 0px 18px -8px rgba(0,0,0,0.75);

    &:not(:last-child) {
      margin-bottom: 1rem;
    }

    .question {
      margin-left: .5rem;
    }

    p:first-of-type {
      margin-bottom: .5rem;
    }
  }
}
</style>

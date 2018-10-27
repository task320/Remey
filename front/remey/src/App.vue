<template>
	<div id="app">
		<FormPostShopping/>
		<div class="shopping-list">
			<SelectYearMonth/>
			<br />
			<ShoppingList/>
		</div>
	</div>
</template>

<script>

import ShoppingList from './components/ShoppingList.vue'
import FormPostShopping from './components/FormPostShopping.vue'
import SelectYearMonth from './components/SelectYearMonth.vue'

export default {
	components: {
		ShoppingList,
		FormPostShopping,
		SelectYearMonth
	}
}
</script>

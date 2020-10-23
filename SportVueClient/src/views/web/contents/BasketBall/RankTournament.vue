<template>
  <v-card>
    <v-card-title>
      <h3>Rank</h3>
      <v-spacer></v-spacer>
    </v-card-title>
    <v-data-table :headers="headers" :items="desserts">
      <template v-slot:item.numberRank="item">
        {{ desserts.indexOf(item.item) + 1 }}
      </template>
      <template v-slot:item.winRate="{ item }">
        <div v-if="item.team.totalmatch != 0">{{ item.rank * 100 }}%</div>
      </template>

      <template v-slot:item.team.logo="{ item }">
        <div>
          <v-img max-height="50" max-width="50" :src="item.team.logo"></v-img>
        </div>
      </template>
      <template v-slot:item.team.idTeam="{ item }">
        <div class="text-center">
          <a
            :href="
              $router.resolve({
                path: '/DetailTeamFootball/' + item.team.idTeam,
              }).href
            "
            style="color: black; margin-left: -30px"
          >
            <v-icon color="red">mdi-chevron-double-right</v-icon>
          </a>
        </div>
      </template>
    </v-data-table>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    headers: [
      {
        text: "#",
        value: "numberRank",
        sortable: false,
      },
      { text: "Team", value: "team.nameTeam" },
      { text: "Logo", value: "team.logo", sortable: false },
      { text: "MP", value: "team.totalmatch" },
      { text: "Win", value: "team.totalwin" },
      { text: "Goals", value: "totalGoals" },
      { text: "Win rate", value: "winRate" },
      { text: "Detail", value: "team.idTeam", sortable: false },
    ],
    desserts: [],
  }),
  created() {
    this.$store
      .dispatch("tournament/getRank", "Basketball")
      .then((response) => {
        this.desserts = response.data;
        console.log(this.desserts);
      });
  },
  methods: {},
};
</script>
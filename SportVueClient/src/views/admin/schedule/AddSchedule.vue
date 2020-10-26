<template>
  <div>
    <b-overlay
      :show="busy"
      rounded
      opacity="0.6"
      spinner-small
      spinner-variant="primary"
    >
      <v-form ref="form">
        <v-autocomplete
          v-model="selectTournament"
          :items="tournament"
          item-text="nameTour"
          item-value="idTour"
          dense
          label="Tournament"
          :rules="[
            (v) => !!v || 'Item is required',
            (v) => v != '' || 'Item is required',
          ]"
        ></v-autocomplete>

        <v-row>
          <v-col class="d-flex" cols="12" sm="6">
            <v-autocomplete
              v-model="selectTeam1"
              :items="team"
              item-text="nameTeam"
              item-value="idTeam"
              dense
              label="Team1"
              :rules="[
                (v) => !!v || 'Item is required',
                (v) => v != '' || 'Item is required',
                (v) =>
                  v != this.selectTeam2 ||
                  'Team1 and team2 cannot be the same team',
              ]"
            ></v-autocomplete>
          </v-col>
          <v-col class="d-flex" cols="12" sm="6">
            <v-autocomplete
              v-model="selectTeam2"
              :items="team"
              item-text="nameTeam"
              item-value="idTeam"
              dense
              label="Team2"
              :rules="[
                (v) => !!v || 'Item is required',
                (v) => v != '' || 'Item is required',
                (v) =>
                  v != this.selectTeam1 ||
                  'Team1 and team2 cannot be the same team',
              ]"
            ></v-autocomplete>
          </v-col>
          <v-col>
            <v-menu
              ref="menu1"
              v-model="menu1"
              :close-on-content-click="false"
              :return-value.sync="menu1"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="timeStart"
                  label="Time start"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  :rules="rulesTimeStart"
                  required
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="timeStart" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu1 = false">
                  Cancel
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu1.save(timeStart)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col>
            <v-menu
              ref="menu2"
              v-model="menu2"
              :close-on-content-click="false"
              :return-value.sync="menu2"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="timeEnd"
                  label="Time start"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  :rules="rulesTimeEnd"
                  required
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="timeEnd" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu2 = false">
                  Cancel
                </v-btn>
                <v-btn text color="primary" @click="$refs.menu1.save(timeEnd)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <div>
          <v-text-field
            label="Address"
            v-model="address"
            required
            :counter="15"
            :rules="[
              (v) => !!v || 'Item is required',
              (v) => v != '' || 'Item is required',
              (v) =>
                (v && v.length <= 15) || 'Name must be less than 15 characters',
            ]"
          ></v-text-field>
          <v-text-field
            label="Title"
            v-model="title"
            :counter="25"
            :rules="[
              (v) => !!v || 'Item is required',
              (v) => v != '' || 'Item is required',
              ,
              (v) =>
                (v && v.length <= 25) || 'Name must be less than 25 characters',
            ]"
          ></v-text-field>
        </div>
        <v-btn class="mx-2" dark color="blue-grey" @click.stop="dialog = true">
          Reset
        </v-btn>
        <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <v-card-title class="headline"> Notification </v-card-title>

            <v-card-text>
              Do you want to Reset without finishing importing?
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn color="green darken-1" text @click="dialog = false">
                Cancel
              </v-btn>

              <v-btn color="green darken-1" text @click="okCancel"> OK </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-btn class="mx-2" dark color="primary" @click="submit">
          Create
        </v-btn>
      </v-form>
    </b-overlay>
  </div>
</template>
<script>
var dateNow = new Date().toISOString().substr(0, 10);
import Schedule from "../../../models/schedule";

export default {
  props: {
    hideModal: {
      type: Function,
    },
    getData: {
      type: Function,
    },
  },
  data: () => ({
    busy: false,
    dialog: false,
    schedule: new Schedule(),
    tournament: [],
    selectTournament: "",
    team: [],
    teamAll: [],
    selectTeam2: "",
    selectTeam1: "",
    timeStart: new Date().toISOString().substr(0, 10),
    timeEnd: new Date().toISOString().substr(0, 10),
    menu1: false,
    menu2: false,
    rulesTimeStart: [(v) => dateNow <= v || "bigger date now"],
    rulesTimeEnd: [],
    address: "",
    title: "",
  }),
  created() {
    this.getTournament();
  },
  methods: {
    okCancel() {
      (this.dialog = false), this.reset();
    },
    getTournament() {
      this.$store.dispatch("tournament/getAction").then((response) => {
        this.tournament = response.data;
      });
    },
    reset() {
      this.$refs.form.reset();
      (this.timeStart = new Date().toISOString().substr(0, 10)),
        (this.timeEnd = new Date().toISOString().substr(0, 10));
    },

    submit() {
      (this.busy = true),
        (this.rulesTimeEnd = [
          (v) => v > this.timeStart || "bigger time start",
        ]);
      if (this.$refs.form.validate() == true) {
        this.schedule.idTour = this.selectTournament;
        this.schedule.timeStart = this.timeStart;
        this.schedule.timeEnd = this.timeEnd;
        this.schedule.idTeam1 = this.selectTeam1;
        this.schedule.idTeam2 = this.selectTeam2;
        this.schedule.address = this.address;
        this.schedule.title = this.title;
        this.$store
          .dispatch("schedule/createSchedule", this.schedule)
          .then((response) => {
            if (response.data == "create") {
              alert("Create");
              this.hideModal();
              this.reset();
              this.getData();
              this.busy = false;
            } else {
              alert(response.data);
              this.busy = false;
              this.hideModal();
              this.getData();
            }
          });
      } else {
        this.$refs.form.validate();
        this.busy = false;
      }
    },
  },
  watch: {
    selectTournament() {
      if (!!this.selectTournament) {
        this.$store
          .dispatch("tournament/getById", this.selectTournament)
          .then((response) => {
            this.team = response.data.team;
            this.teamAll = response.data.team;
            this.selectTeam1 = "";
            this.selectTeam2 = "";
          });
      }
    },
    
  },
};
</script>